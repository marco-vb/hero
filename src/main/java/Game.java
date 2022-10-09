import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Screen screen;
    private int width = 60, height = 30;
    private Arena arena = new Arena(height, width);

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);


            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

        } catch (IOException e) {e.printStackTrace();}
    }

    private void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
        screen.refresh();
    }

    private void processKey (KeyStroke key) throws IOException {
        arena.processKey(key);
    }

    public void run() throws IOException {
        while (true){
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
            if (key.getKeyType() == KeyType.EOF) break;
            processKey(key);
            if (arena.verifyMonsterCollisions()) {
                screen.clear();
                TextGraphics graphics = screen.newTextGraphics();
                //System.out.println("GAME OVER");
                print_lose(graphics);
                screen.refresh();
                while (true){
                    key = screen.readInput();
                    if (key.getKeyType() == KeyType.Enter) break;
                }
                screen.close();
                break;
            }
            if (arena.getCoinNum() == 0) {
                screen.clear();
                TextGraphics graphics = screen.newTextGraphics();
                //System.out.println("YOU WIN");
                print_win(graphics);
                screen.refresh();
                while (true){
                    key = screen.readInput();
                    if (key.getKeyType() == KeyType.Enter) break;
                }
                screen.close();
                break;
            }
        }
    }

    public void print_win (TextGraphics g) {
        g.setBackgroundColor(TextColor.Factory.fromString("#1F2B37"));
        g.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        g.enableModifiers(SGR.BOLD);
        g.setForegroundColor(TextColor.Factory.fromString("#3FFF33"));
        g.putString(new TerminalPosition(width/2 - 4, height/2), "YOU WIN");
        g.putString(new TerminalPosition(width/2 - 8, 3 + height/2), "Enter to quit.");
    }

    public void print_lose (TextGraphics g) {
        g.setBackgroundColor(TextColor.Factory.fromString("#1F2B37"));
        g.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        g.enableModifiers(SGR.BOLD);
        g.setForegroundColor(TextColor.Factory.fromString("#FF335A"));
        g.putString(new TerminalPosition(width/2 - 5, height/2), "GAME OVER");
        g.putString(new TerminalPosition(width/2 - 8, 3 + height/2), "Enter to quit.");
    }
}
