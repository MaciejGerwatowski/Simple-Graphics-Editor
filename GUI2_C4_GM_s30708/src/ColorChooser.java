import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooser implements ActionListener {
    private Component component;
    public static Color newColor;

    public ColorChooser(Component component) {
        this.component = component;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color c = JColorChooser.showDialog(component, "Choose a color", Color.white);

        if(c != null){
            newColor = c;
        }
    }


}
