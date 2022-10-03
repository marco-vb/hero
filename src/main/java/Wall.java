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

    public Position getPosition() {return new Position(this.x, this.y);}

    public void draw(TextGraphics graphics) {
        graphics.putString(new TerminalPosition(this.getX(), this.getY()), "*");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }
}
