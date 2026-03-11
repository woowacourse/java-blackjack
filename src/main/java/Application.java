import controller.BlackjackController;
import infra.CardShufflerImpl;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        new BlackjackController(
                new InputView(),
                new OutputView(),
                new CardShufflerImpl()

        ).run();
    }
}
