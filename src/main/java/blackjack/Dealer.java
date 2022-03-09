package blackjack;

public class Dealer {
    private static final int MORE_CARD_STANDARD = 16;

    private final HoldCards holdCards;

    public Dealer(Card card1, Card card2) {
        holdCards = new HoldCards();
        holdCards.addCard(card1);
        holdCards.addCard(card2);
    }

    public int countCards() {
        return holdCards.countBestNumber();
    }

    public boolean shouldHaveMoreCard() {
        return countCards() <= MORE_CARD_STANDARD;
    }

    public void putCard(Card card) {
        holdCards.addCard(card);
    }
}
