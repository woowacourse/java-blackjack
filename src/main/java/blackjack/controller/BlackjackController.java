package blackjack.controller;

import blackjack.domain.BlackjackManager;
import blackjack.domain.GameResultDto;
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
        OutputView.printDealerHand(dealer);
        OutputView.printPlayersHand(players.toList());

        for (Player player : players.toList()) {
            while (player.isNotBust() && InputView.getHitOrStay(player.getName())) {
                player.receiveCard(dealer.giveCard());
                OutputView.printCards(player);
            }
            if (!player.isNotBust()) {
                OutputView.printPlayerBurst(player.getName());
            }
            OutputView.printCards(player);
        }

        while (dealer.getTotalScore() < 17) {
            dealer.receiveCard(dealer.giveCard());
            OutputView.printDealerHit();
        }

        OutputView.printHandResult(players.toList(), dealer);
        GameResultDto gameResultDto = BlackjackManager.getGameResult(dealer, players);
        OutputView.printGameResult(gameResultDto);
    }
}
