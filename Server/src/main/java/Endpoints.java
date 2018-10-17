import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

import java.io.File;

public abstract class Endpoints {
    private static String root = System.getProperty("user.dir")+"/src/main/resources/webroot/";
    public static void assetsEndpoint(Router router) {
        router.route("/API/*").handler(StaticHandler.create().setCachingEnabled(false));
    }
    public static void htmlEndpoint(Router router,String endpoint,String page){
        router
                .route(endpoint)
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response
                            .putHeader("content-type","text/html")
                            .sendFile(root+page);
                });
    }
}
