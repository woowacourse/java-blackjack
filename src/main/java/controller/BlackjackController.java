package controller;

import service.BlackjackService;
import view.InputView;

public class BlackjackController {

    public void run() {
        BlackjackService blackjackGame = BlackjackService.of(InputView.readPlayersName());
        blackjackGame.start();

        while (blackjackGame.existNextPlayerTurn()) {
            blackjackGame.nextTurn(InputView.readHit());
        }

        blackjackGame.dealerTurn();
    }
}
