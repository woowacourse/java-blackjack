package blackjack.controller;

import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private final BlackjackService blackjackService = new BlackjackService();

    public void run() {
        blackjackService.setPlayer(InputView.readNames());
        OutputView.printStartingCards(blackjackService.drawStartingCards());
    }
}
