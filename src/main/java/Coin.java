import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{
    private Position position;
    public Coin(int x, int y) {position = new Position(x ,y);}
    public Position getPosition() {return this.position;}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FBFF33"));
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "O");
    }
}
