import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena extends Element{

    private int height, width;
    private int num_coins = 10, num_monsters = 5;
    private Hero hero = new Hero(10, 10);
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int h, int w) {
        height = h; width = w; walls = createWalls();
        coins = createCoins(); monsters = createMonsters();
    }

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
        for (int i = 0; i < num_coins; i++){
            Coin new_coin = new Coin(r.nextInt(width - 2) + 1, r.nextInt(height - 2) + 1);
            while (new_coin.getPosition().equals(this.hero.getPosition()) || coins.contains(new_coin)) {
                new_coin = new Coin(r.nextInt(width - 2) + 1, r.nextInt(height - 2));
            }
            coins.add(new_coin);
        }
        return coins;
    }

    private void retrieveCoins(int i) {coins.remove(i);}

    public int getCoinNum() {return coins.size();}

    private List<Monster> createMonsters() {
        Random r = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < num_monsters; i++){
            Monster new_monster = new Monster(r.nextInt(width - 2) + 1, r.nextInt(height - 2) + 1);
            while (new_monster.getPosition().equals(this.hero.getPosition()) ||
                    monsters.contains(new_monster) ||
                    !canHeroMove(new_monster.getPosition(), walls)) {
                new_monster = new Monster(r.nextInt(width - 2) + 1, r.nextInt(height - 2));
            }
            monsters.add(new_monster);
        }

        return monsters;
    }

    public void moveMonsters() {
        // this makes monsters always move towards you, which makes
        // the game impossible
        /*for (Monster m : monsters){
            Position m_pos = m.getPosition();
            Position vector = new Position(hero.getX() - m_pos.getX(),
                    m_pos.getY() - hero.getY());
            int x_inc = vector.getX() > 0 ? 1 : -1;
            int y_inc = vector.getY() > 0 ? -1 : 1;
            if (vector.getX() == 0) {m.move(0, y_inc);}
            if (vector.getY() == 0) {m.move(x_inc, 0);}
            m.setPosition(m.move(x_inc, y_inc));
        }*/

        for (Monster m : monsters) {
            Random r = new Random();
            int x_inc, y_inc;
            do {
                x_inc = r.nextInt(3) - 1;
                y_inc = r.nextInt(3) - 1;
            } while (!canHeroMove(m.move(x_inc, y_inc), walls));
            m.setPosition(m.move(x_inc, y_inc));
        }
    }

    private void moveHero(Position position) {
        if (canHeroMove(position, this.walls)) {
            for (int i = 0; i < this.coins.size(); i++)
                if (coins.get(i).getPosition().equals(this.hero.getPosition())) {
                    retrieveCoins(i);
                    break;
                }

            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position pos, List<Wall> walls ){
        for (Wall w : walls)
            if (w.getPosition().equals(pos)) return false;

        return pos.getX() >= 0 && pos.getX() < width && pos.getY() >= 0 && pos.getY() < height;
    }

    public boolean verifyMonsterCollisions() {
        for (Monster m : monsters)
            if (m.getPosition().equals(hero.getPosition())) return true;

        return false;
    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#1F2B37"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin c : coins)
            c.draw(graphics);
        for (Monster m : monsters)
            m.draw(graphics);
        hero.draw(graphics);
    }
    public void processKey(KeyStroke key){
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
        moveMonsters();
    }
}
