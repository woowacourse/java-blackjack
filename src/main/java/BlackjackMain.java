import controller.BlackjackController;
import view.InputView;
import view.OutputView;

public class BlackjackMain {

    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController(new InputView(), new OutputView());
        controller.run();
    }
}
