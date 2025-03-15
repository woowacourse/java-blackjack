import client.InputParser;
import client.InputProcessor;
import client.OutputFormatter;
import client.OutputPrinter;
import game.BlackjackGame;

public class BlackjackConfig {

    public BlackjackGame blackjackGame() {
        return new BlackjackGame(
                inputView(),
                outputView()
        );
    }

    private InputProcessor inputView() {
        return new InputProcessor(new InputParser());
    }

    private OutputPrinter outputView() {
        return new OutputPrinter(new OutputFormatter());
    }
}
