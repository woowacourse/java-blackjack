import view.InputView;
import view.OutputView;

public class AppConfig {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public InputView inputView() {
        return inputView;
    }
    
    public OutputView outputView() {
        return outputView;
    }
}
