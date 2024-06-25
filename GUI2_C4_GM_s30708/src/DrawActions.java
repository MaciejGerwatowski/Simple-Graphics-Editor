import java.awt.event.ActionEvent;

public class DrawActions {

    public static int whatRWDrawning = 0;
    static DrawingPanel drawingPanel = DrawingPanel.getInstance();
    static StatusBar statusBar = StatusBar.getInstance();

    public static void penAction(ActionEvent e){
        //System.out.println("pen");
        whatRWDrawning = 1;
        statusBar.choosenTool();
    }

    public static void squareAction(ActionEvent e){
        //System.out.println("kwadrat");
        whatRWDrawning = 3;
        statusBar.choosenTool();
    }

    public static void circleAction(ActionEvent e){
        //System.out.println("kolo");
        whatRWDrawning = 2;
        statusBar.choosenTool();
    }

    public static void clearScreen(ActionEvent e){
        drawingPanel.clear();
    }
}
