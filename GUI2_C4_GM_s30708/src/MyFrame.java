import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    static StatusBar statusBar = StatusBar.getInstance();

    private  static MyFrame instance = null;

    public static MyFrame getInstance(){
        if(instance == null){
            instance = new MyFrame();
        }
        return instance;
    }


    private MyFrame() {
        //JFrame jf = new JFrame();
        this.setTitle("Simple Draw");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Tworzenie menu
        JMenuBar menuBar = new JMenuBar();

        FIleMenu fileMenu = new FIleMenu(this);
        menuBar.add(fileMenu);
        DrawMenu drawMenu = new DrawMenu();
        menuBar.add(drawMenu);

        this.setJMenuBar(menuBar);

        //pasek stanu
        StatusBar sb = StatusBar.getInstance();
        sb.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(sb, BorderLayout.SOUTH);
        sb.setFileStatusTxtNonStatic("New");

        //rysowanie
        DrawingPanel drawingPanel = DrawingPanel.getInstance();
        this.add(drawingPanel);


        this.setResizable(true);
        this.setVisible(true);


    }
}
