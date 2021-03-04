package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.GameManager;
import blackjack.domain.game.WinnerCount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    public void run() {
        Dealer dealer = new Dealer();
        Players players = askPlayers(dealer);

        Deck.shuffleCards();
        drawCards(players, dealer);
        drawUntilPossible(dealer, players);

        OutputView.noticePlayersPoint(dealer, players);
        OutputView.printResult(new WinnerCount().calculateTotalWinnings(players));
        printEachPlayerResult(players);
    }

    private Players askPlayers(Dealer dealer) {
        try {
            return new Players(InputView.enterNames(), dealer);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askPlayers(dealer);
        }
    }

    private void printEachPlayerResult(Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printPlayerResult(player);
        }
    }

    private void drawCards(Players players, Dealer dealer) {
        OutputView.noticeDrawTwoCards(players);
        new GameManager(players).giveCards();
        OutputView.noticePlayersCards(dealer, players);
    }

    private void drawUntilPossible(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            askKeepDrawing(player);
        }
        while (dealer.canReceiveCard()) {
            dealer.receiveOneCard();
            OutputView.noticeDealerReceiveCard();
        }
    }

    private void askKeepDrawing(Player player) {
        try {
            playEachPlayer(player);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            playEachPlayer(player);
        }
    }

    private void playEachPlayer(Player player) {
        while (player.canReceiveCard() && player.continueDraw(InputView.isContinueDraw(player))) {
            player.receiveOneCard();
            OutputView.noticePlayerCards(player);
        }
    }
}
