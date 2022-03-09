package blackjack;

public class Dealer extends Person{

    private static final int CONDITION_HIT = 16;
    private Cards cards;

    public Dealer() {
        cards = new Cards();
    }

    public Card handOutCard(NumberGenerator numberGenerator) {
        return cards.pickCard(numberGenerator);
    }

    public boolean isHit() {
        return (score() <= CONDITION_HIT);
    }
}
