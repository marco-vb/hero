import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

public class Hero {
    private Position position = new Position(10, 10);

    public Hero(int x, int y){
        position.setX(x);
        position.setY(y);
    }

    public void setPosition(Position pos){
        position.setX(pos.getX());
        position.setY(pos.getY());
    }
    public int getX() {return position.getX();}
    public int getY() {return position.getY();}

    public Position moveUp() {return new Position(position.getX(), position.getY() - 1);}
    public Position moveDown() {return new Position(position.getX(), position.getY() + 1);}
    public Position moveLeft() {return new Position(position.getX() - 1, position.getY());}
    public Position moveRight() {return new Position(position.getX() + 1, position.getY());}
}
