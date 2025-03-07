import controller.BlackjackController;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        final BlackjackController controller = new BlackjackController(inputView, outputView);

        try {
            controller.gameStart();
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
