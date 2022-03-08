package blackjack;

public class Dealer {

    private Cards cards;

    public Dealer() {
        cards = new Cards();
    }

    public Card handOutCard(NumberGenerator numberGenerator) {
        return cards.pickCard(numberGenerator);
    }
}
