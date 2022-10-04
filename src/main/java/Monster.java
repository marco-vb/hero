import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends Element{
    private Position position;

    public Monster(int x, int y) {position = new Position(x, y);}

    @Override
    public Position getPosition() {return position;}

    @Override
    public void setPosition(Position position) {this.position = position;}

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF33F5"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "Y");
    }

    public Position move(int x_inc, int y_inc) {
        Position pos = new Position(this.getPosition().getX() + x_inc,
                this.getPosition().getY() + y_inc);
        return pos;
    }
}
