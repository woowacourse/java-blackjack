package blackjack.controller;

import blackjack.domain.view.InputView;
import blackjack.service.BlackjackService;

public class BlackjackController {

    private final BlackjackService blackjackService = new BlackjackService();

    public void run() {
        blackjackService.setPlayer(InputView.readNames());
    }
}
