package blackjack;

import java.util.Arrays;

public class Dealer implements User {


    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_THRESHOLD = 16;

    private final Cards cards;

    Dealer () {
        this.cards = new Cards();
    }

    public boolean canHit() {
        return getScore() <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public void hit(Card card) {
        this.cards.addCard(card);
    }

    @Override
    public String getCards() {
        return this.cards.getCards();
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public int getScore() {
        return cards.getScore();
    }

    public String getFirstCard() {
        return Arrays.stream(getCards().split(", ")).findFirst().get();
    }
}
