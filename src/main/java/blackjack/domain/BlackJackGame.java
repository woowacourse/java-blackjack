package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.strategy.CardPicker;

public class BlackJackGame {

    private final CardPool cardPool;

    public BlackJackGame() {
        cardPool = new CardPool();
    }

    public void initHit(Players players, Dealer dealer, CardPicker cardPicker) {
        dealer.initHit(cardPool, cardPicker);
        players.initHit(cardPool, cardPicker);

    }
}
