import strategy.RandomShuffle;
import strategy.ShuffleStrategy;
import view.InputView;
import view.OutputView;

public class AppConfig {

    public GameController controller() {
        return new GameController(inputView(), outputView(), service());
    }

    private GameService service() {
        return new GameService(strategy());
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
}
