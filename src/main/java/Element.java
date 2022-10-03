import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Element {
    private Position position;


    public Position getPosition() {return position;}
    public void setPosition(Position position) {this.position = position;}

    public abstract void draw(TextGraphics graphics);
}
