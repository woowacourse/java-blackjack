package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Map;

public class BlackJackController {
    public void run() {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.enterNames(), dealer);
        //TODO: 예외 잡기
        //TODO: dealer랑 player 합치기
        Deck.shuffleCards();
        drawCards(players, dealer);
        drawUntilPossible(dealer, players);

        WinnerCount winnerCount = new WinnerCount();

        OutputView.noticePlayersPoint(dealer, players);
        OutputView.noticeResult();
        OutputView.printResult(winnerCount.calculateTotalWinnings(players));
        printEachPlayerResult(players);
    }

    private void printEachPlayerResult(Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printPlayerResult(player);
        }
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
            dealer.receiveOneCard();
            OutputView.noticeDealerReceiveCard();
        }
    }

    private void playEachPlayer(Player player) {//TODO: y/n 객체화
        while (player.canReceiveCard() && player.continueDraw(InputView.isContinueDraw(player))) {
            player.receiveOneCard();
            OutputView.noticePlayerCards(player);
        }
    }
}
