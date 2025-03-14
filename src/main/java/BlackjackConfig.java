import client.InputProcessor;
import client.OutputPrinter;
import client.InputParser;
import client.OutputFormatter;

public class BlackjackConfig {

    public BlackjackGame blackjackApplication() {
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
