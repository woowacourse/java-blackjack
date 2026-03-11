package blackjack.service;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final CardDistributor cardDistributor;

    public Game(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
    }

    public int dealerDrawsCardsUntilDone(Participant dealer) {
        int count = 0;
        while (dealer.isDealerNotDone()) {
            cardDistributor.distributeCardToDealer(dealer);
            count++;
        }
        return count;
    }

    // 초기 카드 분배
    public void distributeInitialCards(List<Participant> players, Participant dealer) {
        cardDistributor.distributeInitialCards(players, dealer);
    }

    // 플레이어에게 한 장 분배
    public void drawCardToPlayer(Participant player) {
        cardDistributor.distributeCardToPlayer(player);
    }
}
