package blackjack;

public class Player {
    private final String name;
    private final HoldCards holdCards;

    public Player(String name, Card card1, Card card2) {
        this.name = name;
        holdCards = new HoldCards();
        holdCards.addCard(card1);
        holdCards.addCard(card2);
    }

    public int countCards() {
        return holdCards.countBestNumber();
    }

    public void putCard(Card card) {
        holdCards.addCard(card);
    }
}
