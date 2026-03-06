
public class AppConfig {

    public GameController controller() {
        return new GameController(inputView(), outputView(), service());
    }

    private GameService service() {
        return new GameService();
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
