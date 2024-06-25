import java.awt.*;

public class ColoredPoint {
    Point point;
    Color color = Color.black;

    ColoredPoint(Point point, Color color) {
        this.point = point;
        this.color = color;
    }

    double distance(ColoredPoint cp){
        double px = cp.point.x - point.x;
        double py = cp.point.y - point.y;
        return Math.sqrt(px * px + py * py);
    }


    @Override
    public String toString() {
        if(color == null){
            return "" + point.x + " " + point.y + " " + Color.black.getRGB();
        } else{
        return "" + point.x + " " + point.y + " " + color.getRGB();
        }
    }
} // klasa przechowuje punkt i kolor dla rysowanego obiektu