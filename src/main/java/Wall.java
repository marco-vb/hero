import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class Wall {
    private int x, y;
    public Wall(int x, int y) {this.x = x; this.y = y;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(TextGraphics graphics) {
        graphics.putString(new TerminalPosition(this.getX(), this.getY()), "*");
    }
}
