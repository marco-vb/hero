import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private int height, width;
    private Hero hero = new Hero(10, 10);
    private List<Wall> walls;

    public Arena(int h, int w) {this.height = h; this.width = w; this.walls = createWalls();}

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    private void moveHero(Position position) {
        if (canHeroMove(position, this.walls)) {
            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position pos, List<Wall> walls ){
        for (Wall w : walls)
            if (w.getPosition().equals(pos)) return false;

        if (pos.getX() >= 0 && pos.getX() < 40 && pos.getY() >= 0 && pos.getY() < 20)
            return true;
        return false;
    }

    public void draw(Screen screen) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getX(), hero.getY()), "X");
        for (Wall wall : walls)
            wall.draw(graphics);
    }
    public void processKey(KeyStroke key){
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
    }

}
