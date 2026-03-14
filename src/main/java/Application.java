import controller.BlackjackController;
import domain.card.deckMaker.DeckMaker;
import domain.card.deckMaker.OneRandomDeckMaker;
import domain.hitStrategy.HitStrategy;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.state.generator.BlackjackGenerator;
import domain.state.generator.BustGenerator;
import domain.state.generator.FinishedStateGenerator;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(System.in);
        OutputView outputView = new OutputView(System.out);
        DeckMaker deckMaker = new OneRandomDeckMaker();
        BlackjackController controller = new BlackjackController(inputView, outputView);
        HitStrategy playerHitStrategy = Player.getDefaultHitStrategy();
        HitStrategy dealerHitStrategy = Dealer.getDefaultHitStrategy();
        List<FinishedStateGenerator> finishedStateGenerators = List.of(new BustGenerator(), new BlackjackGenerator());

        controller.start(deckMaker, dealerHitStrategy, playerHitStrategy, finishedStateGenerators);
    }
}
