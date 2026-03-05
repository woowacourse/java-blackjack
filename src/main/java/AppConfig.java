
public class AppConfig {

    public GameController controller() {
        return new GameController(inputView(), service());
    }

    private GameService service() {
        return new GameService();
    }

    private InputView inputView() {
        return new InputView();
    }
}
