import java.awt.*;

public class Square extends MyShape{
    int a;

    public Square(int x, int y, int a, Color color) {
        super(x, y);
        this.a = a;
        this.color = color;
    }

    public Square(int x, int y, int a) {
        super(x, y);
        this.a = a;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x - a/2, y - a/2, a, a);
    }

    public boolean contains(Point p) {
        double distance = Math.sqrt( (p.x - x)*(p.x - x) + (p.y - y)*(p.y - y) );
        return distance <= a/2;
    }
}
