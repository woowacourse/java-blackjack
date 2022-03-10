package blackjack;

public class Player {
    private final String name;
    private final HoldCards holdCards;

    public Player(String name, HoldCards holdCards) {
        this.name = name;
        this.holdCards = holdCards;
    }

    public int countCards() {
        return holdCards.countBestNumber();
    }

    public void putCard(Card card) {
        holdCards.addCard(card);
    }

    public Outcome isWin(int dealerTotal) {
        return Outcome.match(dealerTotal, countCards());
    }

    public String getName() {
        return name;
    }

    public HoldCards getHoldCards() {
        return holdCards;
    }
}
