package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.GamePlayView;
import blackjack.view.GameResultView;
import blackjack.view.GameSetupView;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        createController().run();
    }

    private static BlackjackController createController() {
        Scanner scanner = new Scanner(System.in);
        return new BlackjackController(
                new GameSetupView(scanner),
                new GamePlayView(scanner),
                new GameResultView());
    }
}
