import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena extends Element{

    private int height, width;
    private Hero hero = new Hero(10, 10);
    private List<Wall> walls;
    private List<Coin> coins;

    public Arena(int h, int w) {
        this.height = h; this.width = w; this.walls = createWalls(); this.coins = createCoins();}

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

    private List<Coin> createCoins() {
        Random r = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Coin new_coin = new Coin(r.nextInt(width - 2) + 1, r.nextInt(height - 2) + 1);
            while (new_coin.getPosition().equals(this.hero.getPosition()) || coins.contains(new_coin)) {
                new_coin = new Coin(r.nextInt(width - 2) + 1, r.nextInt(height - 2));
            }
            coins.add(new_coin);
        }
        return coins;
    }

    private void retrieveCoins(int i) {
        coins.remove(i);
    }
    private void moveHero(Position position) {
        if (canHeroMove(position, this.walls)) {
            int index = -1;
            for (int i = 0; i < this.coins.size(); i++)
                if (coins.get(i).getPosition().equals(this.hero.getPosition())) {
                    index = i;
                    retrieveCoins(index);
                    break;
                }

            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position pos, List<Wall> walls ){
        for (Wall w : walls)
            if (w.getPosition().equals(pos)) return false;

        if (pos.getX() >= 0 && pos.getX() < this.width && pos.getY() >= 0 && pos.getY() < this.height)
            return true;
        return false;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin c : coins)
            c.draw(graphics);
        graphics.putString(new TerminalPosition(hero.getX(), hero.getY()), "X");
    }
    public void processKey(KeyStroke key){
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
    }

}
