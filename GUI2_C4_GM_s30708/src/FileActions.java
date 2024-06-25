import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileActions {

    private static Component frame;
    static DrawingPanel drawingPanel = DrawingPanel.getInstance();

    static StatusBar statusBar = StatusBar.getInstance();

    private static File currentFile = null;

    public static File getCurrentFile() {
        return currentFile;
    }

    public static void openFile(ActionEvent e) {
        //System.out.println("Otwórz plik");
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            List<ColoredPoint> fromFileP = new ArrayList<>();
            List<ColoredPoint> fromFileC = new ArrayList<>();
            List<ColoredPoint> fromFileS = new ArrayList<>();


            Pattern pattern = Pattern.compile("([psc]) (\\d+) (\\d+) (-?\\d+)");

            MyFrame.getInstance().setTitle(currentFile.getName());

           // System.out.println(currentFile.getName() );
            statusBar.setFileStatusTxt("Saved");

            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String type = matcher.group(1);
                        int x = Integer.parseInt(matcher.group(2));
                        int y = Integer.parseInt(matcher.group(3));
                        int color = Integer.parseInt(matcher.group(4));
                        //System.out.println(x + " " + y + " " + color);

                        Color c = new Color(color);
                        ColoredPoint cp = new ColoredPoint(new Point(x, y), c);

                        switch (type){
                            case "p" -> fromFileP.add(cp);
                            case "c" -> fromFileC.add(cp);
                            case "s" -> fromFileS.add(cp);
                        }
                    }
                }

                drawingPanel.setPointsL(fromFileP);
                drawingPanel.setPointsC(fromFileC);
                drawingPanel.setPointsS(fromFileS);
                drawingPanel.repaint();

            } catch (IOException ioException) {
                ioException.printStackTrace();
                JOptionPane.showMessageDialog(frame, "File reading error ");
            }
        }
    }

    public static void saveFile(ActionEvent e) {
        //System.out.println("Zapisz plik");
        if (currentFile != null) {
            saveToFile(currentFile);
        } else {
            saveAsFile(e);  // jesli nie ma aktualnego pliku to zapisz jako
        }
        statusBar.setFileStatusTxt("Saved");
    }

    public static void saveAsFile(ActionEvent e) {
        //System.out.println("Zapisz plik jako");
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            saveToFile(fileToSave);
            currentFile = fileToSave;  // zapisz do zmiennej ostan plik
        }
        statusBar.setFileStatusTxt("Saved");

    }

    private static void saveToFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            for(ColoredPoint cp : drawingPanel.getPointsL()){
                writer.write("p " + cp.toString() +"\n");
            }
            for(ColoredPoint cp : drawingPanel.getPointsC()){
                writer.write("c " + cp.toString() +"\n");
            }
            for(ColoredPoint cp : drawingPanel.getPointsS()){
                writer.write("s " + cp.toString() +"\n");
            }
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(frame, "Błąd zapisu pliku: " + ioException.getMessage());
        }
    }


    public static void exit(ActionEvent e){
        if(confirmQuit()){
            saveFile(e);
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

     static private boolean confirmQuit() {
        JPanel jp = new JPanel();
        return JOptionPane.showConfirmDialog(jp, "Do you want to save the file?", "Yes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

}
