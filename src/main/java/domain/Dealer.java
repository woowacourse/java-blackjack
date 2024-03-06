package domain;

import java.util.List;

public class Dealer extends Player {

    private static final int MAX_SCORE_TO_HIT = 16;

    private final CardDeck cardDeck;

    public Dealer(CardDeck cardDeck) {
        super(new Name("딜러"), cardDeck.initHand());
        this.cardDeck = cardDeck;
    }

    Dealer(List<Card> cards) {
        super(new Name("딜러"), new Hand(cards));
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
        return this.getTotalScore() <= MAX_SCORE_TO_HIT;
    }
}
