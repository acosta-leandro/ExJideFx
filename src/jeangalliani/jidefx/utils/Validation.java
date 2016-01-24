package jeangalliani.jidefx.utils;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jidefx.scene.control.decoration.DecorationUtils;
import jidefx.scene.control.decoration.Decorator;
import jidefx.scene.control.field.FormattedTextField;
import jidefx.scene.control.field.verifier.IntegerRangePatternVerifier;

/**
 * @author Jean Carlo Galliani
 */
public class Validation {
    
    public static SimpleBooleanProperty validGroup = new SimpleBooleanProperty(false);
    public static String EMAIL = "";
    public static String OBRIGATORIO = ".{1,45}";
    public static String TELEFONE = ".{13}";
    public static String CPF = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}";
    public static String INTEIRO = "[0-9]{1,9}";
    public static String VARCHAR45 = ".{0,45}";
    public static String REAL = "[0-9]{1,6}(|.[0-9]{1,2})";
    
    public static void validate(TextField field,String pattern){
        field.setUserData(false);
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                addDecoration(newValue.matches(pattern), field);
            }
        });
        
        addDecoration(field.getText().matches(pattern), field);
    }
    
    private static void addDecoration(boolean b, Node field){
        if(b){
            DecorationUtils.install(field, ok());
            field.setUserData(true);
        }else{
            DecorationUtils.install(field, error());
            field.setUserData(false);
        }
        validateGroup((Pane)field.getParent());
    }
    
    public static void toTelefoneField(FormattedTextField field){
        field.getPatternVerifiers().put("hack", new IntegerRangePatternVerifier(-1, -1));
        field.getPatternVerifiers().put("ddd", new IntegerRangePatternVerifier(0,99));
        field.getPatternVerifiers().put("number", new IntegerRangePatternVerifier(0, 9999));
        field.setPattern("hack(ddd)number-number");
    }
    
    public static void toIntegerField(FormattedTextField field){
        field.getPatternVerifiers().put("int", new IntegerRangePatternVerifier(0, 999999999));
        field.setPattern("int");
    }
    
    public static Decorator error() {
        return new Decorator(new ImageView(new Image("/jidefx/scene/control/decoration/overlay_error.png")));
    }

    public static Decorator ok() {
        return new Decorator(new ImageView(new Image("/jidefx/scene/control/decoration/overlay_correct.png")));
    }
    
    public static SimpleBooleanProperty validateGroup(Pane pane){
        
        for(Node node:pane.getChildren()){
            if(node instanceof Control){
                if(node.getUserData() instanceof Boolean){
                    if(!((boolean)node.getUserData())){
                        validGroup.setValue(false);
                        return validGroup;
                    }
                }
            }
        }
        validGroup.setValue(true);
        return validGroup;
    }

    public static void validate(ComboBox field) {
        field.setUserData(false);
        field.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                addDecoration(newValue != null, field);
            }
        });
        
        addDecoration(field.getValue() != null, field);
    }
}