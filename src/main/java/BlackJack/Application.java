package BlackJack;

import BlackJack.controller.BlackjackController;
import BlackJack.dto.UserDto;
import BlackJack.view.InputView;
import BlackJack.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        blackjackController.run();

    }

}
