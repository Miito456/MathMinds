package Validation;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

public class validar {

    private static boolean contieneSoloNumeros(String texto) {
        return texto.matches("^[\\-\\d., ]+$");
    }

    public static void validarNumerico(JTextComponent componente) {
        ((AbstractDocument) componente.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
                if (contieneSoloNumeros(newText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset);
                if (contieneSoloNumeros(newText)) {
                    super.insertString(fb, offset, text, attr);
                }
            }
        });
    }
}
