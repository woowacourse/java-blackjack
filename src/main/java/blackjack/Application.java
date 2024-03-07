package blackjack;

import blackjack.view.InputView;
import blackjack.view.OutputView;

class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Controller controller = new Controller(inputView, outputView);

        controller.run();
    }
}
