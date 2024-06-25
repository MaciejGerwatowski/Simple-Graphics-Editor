import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class FIleMenu extends JMenu {

    private JFrame frame;
    public FIleMenu(JFrame frame) {
        super("File");
        this.frame = frame;

        JMenuItem newItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        JMenuItem exitItem = new JMenuItem("Quit");

        // skroty klawiszowe
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));

        newItem.addActionListener(FileActions::openFile);
        saveItem.addActionListener(FileActions::saveFile);
        saveAsItem.addActionListener(FileActions::saveAsFile);
        exitItem.addActionListener(FileActions::exit);

        add(newItem);
        add(saveItem);
        add(saveAsItem);
        addSeparator();
        add(exitItem);
    }

}
