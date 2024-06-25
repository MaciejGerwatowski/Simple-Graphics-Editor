import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseMotionListener;

public class Pen extends MyShape {
    private int x2;
    private int y2;


    public Pen(int x, int y, int x2, int y2, Color color) {
        super(x, y);
        this.x2=x2;
        this.y2=y2;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x,y,x2,y2);
    }
}
