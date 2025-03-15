import game.BlackJackGame;
import io.ConsoleInput;
import io.ConsoleOutput;

public class Application {
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame(new ConsoleInput(), new ConsoleOutput());
        game.play();
    }
}
