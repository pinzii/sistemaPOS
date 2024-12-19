package Modelo;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class Eventos {

    public void textKeyPress(KeyEvent evt) {
// declaramos una variable y le asignamos un evento
        char carac = evt.getKeyChar();
        if ((carac < 'a' || carac > 'z') && (carac < 'A' || carac > 'Z')
                && (carac != (char) KeyEvent.VK_BACK_SPACE) && (carac != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }

    public void numberKeyPress(KeyEvent evt) {
// declaramos una variable y le asignamos un evento
        char carac = evt.getKeyChar();
        if ((carac < '0' || carac > '9') && (carac != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }

    public void numberDecimalKeyPress(KeyEvent evt, JTextField textField) {
// declaramos una variable y le asignamos un evento
        char carac = evt.getKeyChar();
        if ((carac < '0' || carac > '9') && textField.getText().contains(".") && (carac != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else if ((carac < '0' || carac > '9') && (carac != '.') && (carac != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }
}
