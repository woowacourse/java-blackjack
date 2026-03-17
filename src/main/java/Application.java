import controller.BlackJackController;
import model.cardpicker.RandomCardPicker;
import view.InputView;
import view.OutputView;

public final class Application {
    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(new InputView(),
                new OutputView(), new RandomCardPicker());
        blackJackController.run();
    }
}
