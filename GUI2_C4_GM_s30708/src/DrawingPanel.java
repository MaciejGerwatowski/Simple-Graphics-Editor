import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel {

    private List<ColoredPoint> pointsL = new ArrayList<>();
    private List<ColoredPoint> pointsC = new ArrayList<>(); //lista przechowuje pozycje i kolor kolek
    private List<ColoredPoint> pointsS = new ArrayList<>(); // lista przechowuje pozycje i kolor kwadracikow
    private Point mousePosition;
    private boolean toDelete;
    static StatusBar statusBar = StatusBar.getInstance();

    public List<ColoredPoint> getPointsL() {
        return pointsL;
    }

    public List<ColoredPoint> getPointsC() {
        return pointsC;
    }

    public List<ColoredPoint> getPointsS() {
        return pointsS;
    }

    public void setPointsL(List<ColoredPoint> pointsL) {
        this.pointsL = pointsL;
    }

    public void setPointsC(List<ColoredPoint> pointsC) {
        this.pointsC = pointsC;
    }

    public void setPointsS(List<ColoredPoint> pointsS) {
        this.pointsS = pointsS;
    }

    //singleton
    private static DrawingPanel instance = null;
    public static DrawingPanel getInstance() {
        if (instance == null) {
            instance = new DrawingPanel();
        }
        return instance;
    }



    private DrawingPanel() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //pen
                if(DrawActions.whatRWDrawning == 1)
                    pointsL.add(new ColoredPoint(e.getPoint(), ColorChooser.newColor) );
                repaint();

                //usuwanie
                if(toDelete) {
                    for (Iterator<ColoredPoint> it = pointsC.iterator(); it.hasNext(); ) {
                        ColoredPoint cp = it.next();
                        if (new Circle(cp.point.x, cp.point.y, 20).contains(e.getPoint())) {
                            if(confirmDelete()) {
                                it.remove();
                                statusBar.setFileStatusTxtNonStatic("Modified");
                                repaint();
                                return;
                            }
                        }
                    }
                    for (Iterator<ColoredPoint> it = pointsS.iterator(); it.hasNext(); ) {
                        ColoredPoint cp = it.next();
                        if (new Square(cp.point.x, cp.point.y, 40).contains(e.getPoint())) {
                            if(confirmDelete()) {
                                it.remove();
                                statusBar.setFileStatusTxtNonStatic("Modified");
                                repaint();
                                return;
                            }
                        }
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if(DrawActions.whatRWDrawning == 1){
                    pointsL.add(new ColoredPoint(e.getPoint(), ColorChooser.newColor) );
                    statusBar.setFileStatusTxtNonStatic("Modified");
                }
                mousePosition = e.getPoint();
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition = e.getPoint();
            }
        });

        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //nic
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_D){
                    toDelete = true;
                }

                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    if(DrawActions.whatRWDrawning == 2 && mousePosition != null)
                        pointsC.add(new ColoredPoint(mousePosition, getRandomColor()));
                    if(DrawActions.whatRWDrawning == 3 && mousePosition!=null)
                        pointsS.add(new ColoredPoint(mousePosition, getRandomColor()));
                    statusBar.setFileStatusTxtNonStatic("Modified");
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    toDelete = false;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int i = 1; i < pointsL.size(); i++) {
            ColoredPoint p1 = pointsL.get(i - 1);
            ColoredPoint p2 = pointsL.get(i);
            if (p1.distance(p2) < 20) {  // Rysuj linię tylko wtedy, gdy punkty są blisko siebie
                Pen p = new Pen(p1.point.x, p1.point.y, p2.point.x, p2.point.y, p1.color);
                p.draw(g);
            }
        }

        for (ColoredPoint cp : pointsC) {
            Circle c = new Circle(cp.point.x, cp.point.y, 20, cp.color);
            c.draw(g);
        }
        for(ColoredPoint cp : pointsS){
            Square s = new Square(cp.point.x, cp.point.y, 40, cp.color);
            s.draw(g);
        }
    }



    public void clear() {
        pointsL.clear();
        pointsC.clear();
        pointsS.clear();
        repaint();
    }

    private Random random = new Random();

    private Color getRandomColor() {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }



    private boolean confirmDelete() {
        return JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this element?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }


}