package blackjack;

public class Dealer {
    private static final int MORE_CARD_STANDARD = 16;

    private final HoldCards holdCards;

    public Dealer(HoldCards holdCards) {
        this.holdCards = holdCards;
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
