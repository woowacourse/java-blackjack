import controller.BlackjackController;
import domain.RandomValueGenerator;
import domain.RandomValueGeneratorImpl;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(System.in);
        OutputView outputView = new OutputView(System.out);

        RandomValueGenerator randomValueGenerator = new RandomValueGeneratorImpl();
        BlackjackController controller = new BlackjackController(inputView, outputView);

        controller.start(randomValueGenerator);
    }
}
