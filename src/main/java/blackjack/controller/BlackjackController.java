package blackjack.controller;

import blackjack.domain.BlackjackManager;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void play() {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.getPlayerNames());

        BlackjackManager.initGame(players, dealer);
        OutputView.printInitGame(players.toList());
        OutputView.printDealer(dealer);
        OutputView.printPlayers(players.toList());

        for (Player player : players.toList()) {
            while (InputView.getHitOrStay(player.getName())) {
                player.receiveCard(dealer.giveCard());
                OutputView.printCards(player);
            }
            OutputView.printCards(player);
        }

        while (dealer.getTotalScore() < 17) {
            dealer.receiveCard(dealer.giveCard());
            OutputView.printDealerHit();
        }

        OutputView.printResult(players.toList(), dealer);

    }
}
