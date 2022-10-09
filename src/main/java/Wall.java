import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    private Position position;
    public Wall(int x, int y) {position = new Position(x, y);}
    public Position getPosition() {return position;}

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#599F89"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }

}
