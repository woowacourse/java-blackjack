import game.BlackJackGame;
import io.ConsoleInput;
import io.ConsoleOutput;
import strategy.DeckShuffleStrategy;

public class Application {
    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput();
        ConsoleOutput output = new ConsoleOutput();
        BlackJackGame game = BlackJackGame.registerParticipants(new DeckShuffleStrategy(), input);
        game.play(input, output);
    }
}
