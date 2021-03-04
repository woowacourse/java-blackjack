package blackjack.controller;

import blackjack.domain.BlackjackManager;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void play() {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.getPlayerNames());
        BlackjackManager blackjackManager = new BlackjackManager(dealer, players);

        blackjackManager.initGame();
        OutputView.printInitGame(blackjackManager.getPlayers());
        OutputView.printCards(blackjackManager.getPlayers());
    }
}
