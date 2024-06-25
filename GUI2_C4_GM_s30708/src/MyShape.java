import java.awt.*;


abstract class MyShape {
    protected int x;
    protected int y;
    protected Color color;



    public MyShape(int x, int y){
        this.x=x;
        this.y=y;
    }

    public abstract void draw(Graphics g);

}
