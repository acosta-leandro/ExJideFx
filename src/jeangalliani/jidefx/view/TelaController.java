package jeangalliani.jidefx.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import jeangalliani.jidefx.utils.Validation;
import jidefx.scene.control.decoration.DecorationPane;
import jidefx.scene.control.field.FormattedTextField;

/**
  * @author Jean Carlo Galliani
  * & Leandro Acosta
 */
public class TelaController implements Initializable {
    
    @FXML
    private TextField tCpf;

    @FXML
    private TextField tEmail;

    @FXML
    private FormattedTextField tInteiro;

    @FXML
    private Button tExemplo;

    @FXML
    private TextField tReal;

    @FXML
    private TextField tCnpj;

    @FXML
    private FormattedTextField tTelefone;

    @FXML
    private TextField tVarchar45;
    
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane anchor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // Importante para que a decoração apareça
        DecorationPane decorationPane = new DecorationPane(anchor);
        root.getChildren().add(decorationPane);
        //**
        
        Validation.toTelefoneField(tTelefone);
        Validation.toIntegerField(tInteiro);
        Validation.validate(tReal, Validation.REAL);
        Validation.validate(tCnpj, Validation.CNPJ);
        Validation.validate(tCpf, Validation.CPF);
        Validation.validate(tEmail, Validation.EMAIL);
        Validation.validate(tVarchar45, Validation.VARCHAR45);
        
        // Quando tudo for validado o botão é disable false(validGroup.not())
        tExemplo.disableProperty().bind(Validation.validGroup.not());
    }    
}