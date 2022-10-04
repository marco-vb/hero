import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element{
    private Position position = new Position(10, 10);

    public Hero(int x, int y){
        position.setX(x);
        position.setY(y);
    }

    public void setPosition(Position pos){
        position.setX(pos.getX());
        position.setY(pos.getY());
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(new TerminalPosition(this.getX(), this.getY()), "X");
    }

    public int getX() {return position.getX();}
    public int getY() {return position.getY();}

    public Position getPosition() {return this.position;}

    public Position moveUp() {return new Position(position.getX(), position.getY() - 1);}
    public Position moveDown() {return new Position(position.getX(), position.getY() + 1);}
    public Position moveLeft() {return new Position(position.getX() - 1, position.getY());}
    public Position moveRight() {return new Position(position.getX() + 1, position.getY());}
}
