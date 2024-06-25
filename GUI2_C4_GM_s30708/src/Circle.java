import java.awt.*;

public class Circle extends MyShape{
    int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y);
        this.radius = radius;
        this.color = color;
    }

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public boolean contains(Point p) {
        double distance = Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
        return distance <= radius;
    }
}
