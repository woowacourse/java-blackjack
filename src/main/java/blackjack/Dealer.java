package blackjack;

public class Dealer {
    private final HoldCards holdCards;

    public Dealer(Card card1, Card card2) {
        holdCards = new HoldCards();
        holdCards.addCard(card1);
        holdCards.addCard(card2);
    }

    public int countCards() {
        return holdCards.countBestNumber();
    }
}
