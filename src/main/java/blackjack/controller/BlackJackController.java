package blackjack.controller;

import blackjack.domain.Players;
import blackjack.view.InputView;

public class BlackJackController {
    public void run() {
        Players players = new Players(InputView.scanPlayerNames());
    }
}
