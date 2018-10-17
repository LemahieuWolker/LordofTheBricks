import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class Server {
    private int port = 8080;
    public static void main(String[] args) {
        new Server().run();
    }
    private void run() {
        //code
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        Router router  = Router.router(vertx);
        Endpoints.htmlEndpoint(router,"/","login.html");
        Endpoints.htmlEndpoint(router,"/menu","menu.html");
        Endpoints.htmlEndpoint(router,"/clan","clan.html");
        Endpoints.htmlEndpoint(router,"/shop","shop.html");
        Endpoints.htmlEndpoint(router,"/game","game.html");
        Endpoints.assetsEndpoint(router);
        httpServer.requestHandler(router::accept).listen(port);
        System.out.println("Server is started on http://localhost:"+port);
    }
    //functies
}
