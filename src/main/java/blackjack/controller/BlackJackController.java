package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.GameManager;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    public void run() {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.enterNames(), dealer);

        drawCards(players, dealer);
        drawUntilPossible(dealer, players);

        OutputView.noticePlayersPoint(dealer, players);
        OutputView.noticeResult(players);
    }

    private void drawCards(Players players, Dealer dealer) {
        OutputView.noticeDrawTwoCards(players);
        GameManager gameManager = new GameManager(players);
        gameManager.giveCards();
        OutputView.noticePlayersCards(dealer, players);
    }

    private void drawUntilPossible(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            playEachPlayer(player);
        }
        while (dealer.canReceiveCard()) {
            dealer.keepDrawing();
            OutputView.noticeDealerReceiveCard();
        }
    }

    private void playEachPlayer(Player player) {
        while (player.canReceiveCard() && player.continueDraw(InputView.isContinueDraw(player))) {
            player.playEachPlayer();
            OutputView.noticePlayerCards(player);
        }
    }
}
