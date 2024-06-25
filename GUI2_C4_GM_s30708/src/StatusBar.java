import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusBar extends JToolBar {

    JLabel tool;
    static JLabel fileStatus;
    private StatusBar() {
        tool = new JLabel("");
        fileStatus = new JLabel("");
        setLayout(new BorderLayout());
        add(tool, BorderLayout.WEST);
        add(fileStatus, BorderLayout.EAST);
    }


    private static StatusBar instance = null;
    public static StatusBar getInstance() {
        if (instance == null) {
            instance = new StatusBar();
        }
        return instance;
    }



    public  void choosenTool(){

        switch (DrawActions.whatRWDrawning){
            case 1 -> { tool.setText( "Pen"); break; }
            case 2 -> { tool.setText( "Circle"); break;}
            case 3 -> {tool.setText( "Square"); break;}
            default -> {tool.setText( ""); }
        }

    }
    public static void setFileStatusTxt(String status){
        switch (status){
            case "Saved" -> { fileStatus.setText("Saved"); break; }
            case "Modified" -> { fileStatus.setText("Modified"); break;}
            default -> {fileStatus.setText("New"); }
        }

    }

    public  void setFileStatusTxtNonStatic(String status){
        switch (status){
            case "Saved" -> { fileStatus.setText("Saved"); break; }
            case "Modified" -> { fileStatus.setText("Modified"); break;}
            default -> {fileStatus.setText("New"); }
        }

    }


    @Override
    public void setBorder(Border border) {
        super.setBorder(border);
    }


}
