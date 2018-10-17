import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.net.HttpURLConnection;
import java.net.URL;

public class GuiController {
    private String url = "http://localhost:8080";
    @FXML
    private WebView webvieuw;
    @FXML
    void initialize() {
        webvieuw.setContextMenuEnabled(false);
        WebEngine engine = webvieuw.getEngine();
        loadPage(engine);
    }

    private void loadPage(WebEngine engine){
        if (canConnectTo()){engine.load(url);}
        else {showErrorPage(engine);}
    }

    private boolean canConnectTo(){
        try{
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();
            return connection.getResponseCode() == 200;
        }catch (Exception ex){return false;}
    }

    private void showErrorPage(WebEngine engine){
        engine.load(getClass().getResource("/web/errorPage.html").toExternalForm());
        engine.getLoadWorker().stateProperty().addListener(
                (ov, oldState, newState) -> {
                    if (newState == Worker.State.SUCCEEDED && engine.getLocation().contains("web/errorPage.html")) {
                        JSObject jso = (JSObject) engine.executeScript("window");
                        jso.setMember("java", new JsHandler());
                    }
                });
    }
    // JavaScript interface
    public class JsHandler {
        public void reboot() {initialize();}
    }
}