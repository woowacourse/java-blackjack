import controller.BlackjackController;
import domain.CardShufflerImpl;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        new BlackjackController(
                new InputView(),
                new OutputView(),
                new BlackjackService(
                        new CardShufflerImpl()
                )
        ).run();
    }
}
