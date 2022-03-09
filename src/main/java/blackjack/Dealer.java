package blackjack;

public class Dealer extends Person{

    private Cards cards;

    public Dealer() {
        cards = new Cards();
    }

    public Card handOutCard(NumberGenerator numberGenerator) {
        return cards.pickCard(numberGenerator);
    }
}
