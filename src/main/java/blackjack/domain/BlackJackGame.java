package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
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

    public void hit(Participant participant, CardPicker cardPicker) {
        participant.hit(cardPool.draw(cardPicker));
    }

    public int calculateScore(Participant participant) {
        return participant.calculateScore();
    }
}
