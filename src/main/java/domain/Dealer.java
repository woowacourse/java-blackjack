package domain;

import meesage.OutputMessage;

public class Dealer {

    private final Cards cards;

    private Dealer(Cards cards) {
        this.cards = cards;
    }

    public static Dealer of(Cards cards) {
        return new Dealer(cards);
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public Cards getCards() {
        return cards;
    }

    public int getScoreOrZeroIfBust(){
        return cards.getScoreOrZeroIfBust();
    }

    public boolean shouldHit() {
        return cards.calculateScore() < Policy.DEALER_HIT_THRESHOLD;
    }
}