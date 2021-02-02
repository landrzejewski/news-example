package pl.training.news.adapters.persistence;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.ODirection;
import com.orientechnologies.orient.core.record.OVertex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.training.news.domain.Article;
import pl.training.news.domain.ArticlesRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
class OrientDbArticlesRepository implements ArticlesRepository {

    private static final String SOURCE_CLASS_NAME = "Source";
    private static final String SOURCE_PROPERTY_NAME = "name";
    private static final String ARTICLE_CLASS_NAME = "Article";
    private static final String AUTHOR_PROPERTY_NAME = "Author";
    private static final String TITLE_PROPERTY_NAME = "Title";
    private static final String PUBLISHED_CLASS_NAME  = "Published";

    private final OrientDB database;
    private final String training;
    private final String user;
    private final String password;

    public OrientDbArticlesRepository(OrientDB database,
                                      @Value("${orientdb.database}") String training,
                                      @Value("${orientdb.user}") String user,
                                      @Value("${orientdb.password}") String password) {
        this.database = database;
        this.training = training;
        this.user = user;
        this.password = password;
        var session = getSession();
        if (session.getClass(SOURCE_CLASS_NAME) == null) {
            var person = session.createVertexClass(SOURCE_CLASS_NAME);
            person.createProperty(SOURCE_PROPERTY_NAME, OType.STRING);
        }
        if (session.getClass(ARTICLE_CLASS_NAME) == null) {
            var person = session.createVertexClass(ARTICLE_CLASS_NAME);
            person.createProperty(AUTHOR_PROPERTY_NAME, OType.STRING);
            person.createProperty(TITLE_PROPERTY_NAME, OType.STRING);
        }
        if (session.getClass(PUBLISHED_CLASS_NAME) == null) {
            session.createEdgeClass(PUBLISHED_CLASS_NAME);
        }
    }

    private ODatabaseSession getSession() {
        return database.open(training, user, password);
    }

    @Override
    public void saveAll(List<Article> articles) {
        articles.forEach(article -> {
            var session = getSession();
            var sourceVertex = addSource(session, article);
            var articleVertex = addArticle(session, article);
            if (!articleVertex.getEdges(ODirection.IN).iterator().hasNext()) {
                sourceVertex.addEdge(articleVertex, PUBLISHED_CLASS_NAME).save();
            }
        });
    }

    private OVertex addSource(ODatabaseSession session, Article article) {
        var source = getFirst(session, "select from Source where name = :name", Map.of("name", article.getSource()));
        if (source.isEmpty()) {
            var vertex = session.newVertex(SOURCE_CLASS_NAME);
            vertex.setProperty(SOURCE_PROPERTY_NAME, article.getSource());
            vertex.save();
            return vertex;
        }
        return source.get();
    }

    private OVertex addArticle(ODatabaseSession session, Article article) {
        var existingArticle = getFirst(session, "select from Article where title = :title", Map.of("title", article.getTitle()));
        if (existingArticle.isEmpty()) {
            var vertex = session.newVertex(ARTICLE_CLASS_NAME);
            vertex.setProperty(TITLE_PROPERTY_NAME, article.getTitle());
            vertex.setProperty(AUTHOR_PROPERTY_NAME, article.getAuthor());
            vertex.save();
            return vertex;
        }
        return existingArticle.get();
    }

    private Optional<OVertex> getFirst(ODatabaseSession session, String query, Map<String, String> parameters) {
        var result = session.query(query, parameters);
        if (result.hasNext()) {
            return Optional.of(result.next().getVertex().get());
        }
        return Optional.empty();
    }


}
