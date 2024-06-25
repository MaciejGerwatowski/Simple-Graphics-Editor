import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class DrawMenu extends JMenu {
    public DrawMenu(){
        super("Draw");
        JMenu figure = new JMenu("Figure");

        ButtonGroup figureGroup = new ButtonGroup();
        JRadioButtonMenuItem circle = new JRadioButtonMenuItem("Circle");
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Square");
        JRadioButtonMenuItem pen = new JRadioButtonMenuItem("Pen");
        JRadioButtonMenuItem clear = new JRadioButtonMenuItem("Clear");


        circle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        square.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        pen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

        circle.addActionListener(DrawActions::circleAction);
        square.addActionListener(DrawActions::squareAction);
        pen.addActionListener(DrawActions::penAction);
        clear.addActionListener(DrawActions::clearScreen);

        figureGroup.add(circle);
        figureGroup.add(square);
        figureGroup.add(pen);

        figure.add(circle);
        figure.add(square);
        figure.add(pen);
        JMenuItem color = new JMenuItem("Color");


        ColorChooser cc = new ColorChooser(this);

        color.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        color.addActionListener(cc);

        add(color);
        add(figure);
        addSeparator();
        add(clear);


    }
}
