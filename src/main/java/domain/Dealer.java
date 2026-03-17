package domain;

import java.util.Collections;
import java.util.List;

public class Dealer {
    public static final String DEALER_NAME = "딜러";
    public static final int ADDITIONAL_THRESHOLD = 16;

    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
    }

    public void add(Card card) {
        cards.addCard(card);
    }

    public void addInitializedCard(Deck deck) {
        add(deck.pop());
        add(deck.pop());
    }

    public boolean isBust() {
        return cards.getFinalScore() > Game.BLACKJACK_VALUE;
    }

    public boolean isDealerBlackjack() {
        return cards.isBlackjack();
    }

    public boolean needAdditinalCard() {
        return getCardsTotalSum() <= ADDITIONAL_THRESHOLD;
    }

    public String getName() {
        return DEALER_NAME;
    }

    public int getCardsTotalSum() {
        return cards.getFinalScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

}
