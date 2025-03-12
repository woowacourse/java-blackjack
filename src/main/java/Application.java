import controller.BlackjackController;
import view.InputView;
import view.OutputView;

public final class Application {

  public static void main(String[] args) {
    final InputView inputView = new InputView();
    final OutputView outputView = new OutputView();
    final BlackjackController blackjackController = new BlackjackController(inputView, outputView);

    blackjackController.run();
  }
}
