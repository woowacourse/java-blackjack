package domain.player;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Hand;
import domain.game.Score;
import java.util.List;

public class Dealer extends Player {

    static final String DEALER_NAME = "딜러";

    private static final int HITTABLE_MAX_SCORE = 16;

    private final CardDeck cardDeck;

    public Dealer(final CardDeck cardDeck) {
        super(new PlayerName(DEALER_NAME), cardDeck.initHand());
        this.cardDeck = cardDeck;
    }

    public Dealer(final List<Card> cards) {
        super(new PlayerName(DEALER_NAME), new Hand(cards));
        this.cardDeck = null;
    }

    public Hand dealHand() {
        return cardDeck.initHand();
    }

    public Card dealCard() {
        return cardDeck.draw();
    }

    @Override
    public boolean isHittable() {
        return this.getScore().compareTo(Score.from(HITTABLE_MAX_SCORE)) <= 0;
    }
}
