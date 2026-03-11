import domain.RandomShuffle;
import domain.ShuffleStrategy;
import strategy.BettingRule;
import strategy.DefaultBettingRule;
import view.InputView;
import view.OutputView;

public class AppConfig {

    public GameController controller() {
        return new GameController(inputView(), outputView(), service());
    }

    private GameService service() {
        return new GameService(strategy(), bettingRule());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private ShuffleStrategy strategy() {
        return new RandomShuffle();
    }

    private BettingRule bettingRule() {
        return new DefaultBettingRule();
    }
}
