import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{
    private Position position;

    public Coin(int x, int y) {this.position = new Position(x ,y);}

    public int getX() {return this.position.getX();}
    public int getY() {return this.position.getY();}
    public Position getPosition() {return this.position;}

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.putString(new TerminalPosition(this.getX(), this.getY()), "O");
    }


}
