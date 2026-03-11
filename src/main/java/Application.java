import controller.BlackjackController;
import domain.card.CardShufflerImpl;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(final String[] args) {
        new BlackjackController(
                new InputView(),
                new OutputView(),
                new BlackjackService(
                        new CardShufflerImpl()
                )
        ).run();
    }
}
