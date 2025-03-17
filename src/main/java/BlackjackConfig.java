import client.InputParser;
import client.InputProcessor;
import client.OutputFormatter;
import client.OutputPrinter;
import game.BlackjackGame;

public class BlackjackConfig {

    public BlackjackGame blackjackGame() {
        return new BlackjackGame(
                inputProcessor(),
                outputProcessor()
        );
    }

    private InputProcessor inputProcessor() {
        return new InputProcessor(new InputParser());
    }

    private OutputPrinter outputProcessor() {
        return new OutputPrinter(new OutputFormatter());
    }
}
