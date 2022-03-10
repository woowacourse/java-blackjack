import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.BlackJackGameController;
import view.InputView;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		BlackJackGameController blackJackGameController = new BlackJackGameController(new InputView(), new OutputView());
		blackJackGameController.gameStart();
	}

}
