import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element{
    private Position position = new Position(10, 10);

    public Hero(int x, int y){
        position.setX(x);
        position.setY(y);
    }


    public Position getPosition() {return this.position;}

    public void setPosition(Position pos){
        position = pos;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.enableModifiers(SGR.BOLD); // bold hero
        graphics.setForegroundColor(TextColor.Factory.fromString("#3FFF33"));
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "H");
    }

    public Position moveUp() {return new Position(position.getX(), position.getY() - 1);}
    public Position moveDown() {return new Position(position.getX(), position.getY() + 1);}
    public Position moveLeft() {return new Position(position.getX() - 1, position.getY());}
    public Position moveRight() {return new Position(position.getX() + 1, position.getY());}
}
