import controller.BlackjackApplication;
import domain.CardFactory;
import domain.CardGiver;
import domain.Deck;
import view.ConsoleView;
import view.InputView;
import view.OutputView;
import view.support.InputParser;
import view.support.OutputFormatter;

public class BlackjackConfig {

    public BlackjackApplication blackjackApplication() {
        return new BlackjackApplication(new ConsoleView(inputView(), outputView()), cardGiver());
    }

    private InputView inputView() {
        return new InputView(new InputParser());
    }

    private OutputView outputView() {
        return new OutputView(new OutputFormatter());
    }

    private CardGiver cardGiver() {
        return new CardGiver(new Deck(CardFactory.createShuffledCards()));
    }
}
