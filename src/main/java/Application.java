import game.BlackJackGame;
import io.ConsoleInput;
import io.ConsoleOutput;

public class Application {
    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput();
        ConsoleOutput output = new ConsoleOutput();

        BlackJackGame game = new BlackJackGame();
        game.play(input, output);
    }
}
