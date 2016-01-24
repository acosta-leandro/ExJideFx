package jeangalliani.jidefx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Jean Carlo Galliani
 */
public class JideFX extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/jeangalliani/jidefx/view/Tela.fxml"))));
        stage.setTitle("Exemplo JideFX");
        stage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}