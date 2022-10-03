import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.TerminalScreen;

public class Hero {
    private int x, y;

    public Hero(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}

    public void moveUp() {this.y--;}
    public void moveDown() {this.y++;}
    public void moveLeft() {this.x--;}
    public void moveRight() {this.x++;}

    public void draw(TerminalScreen screen) {
        screen.setCharacter(this.x, this.y, TextCharacter.fromCharacter('X')[0]);
    }
}
