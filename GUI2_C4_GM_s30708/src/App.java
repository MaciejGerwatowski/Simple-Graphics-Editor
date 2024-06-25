import javax.swing.*;
import java.awt.*;


public class App {
    public static void main(String[] args){
        new App();
    }

    public App() {
        SwingUtilities.invokeLater(this::createGUI);
    }

    protected void createGUI() {
    MyFrame myFrame = MyFrame.getInstance();

        }
    }
