package blackjack.service;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final CardDistributor cardDistributor;
    private final Referee referee;

    public Game(CardDistributor cardDistributor, Referee referee) {
        this.cardDistributor = cardDistributor;
        this.referee = referee;
    }

    public void dealerDrawsCardsUntilDone(Dealer dealer) {
        while (!dealer.isDealerDone()) {
            cardDistributor.distributeCardToDealer(dealer);
        }
    }

    // 초기 카드 분배
    public void distributeInitialCards(List<Player> players, Dealer dealer) {
        cardDistributor.distributeInitialCards(players, dealer);
    }

    // 플레이어에게 한 장 분배
    public void drawCardToPlayer(Player player) {
        cardDistributor.distributeCardToPlayer(player);
    }

    public GameResult judgeTotalGameResult(List<Player> players, Dealer dealer) {
        return referee.judgeResult(players, dealer);
    }
}
