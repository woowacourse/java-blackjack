package blackjack.domain;

public class Dealer extends Gamer {
    private static final String name = "딜러";

    public Dealer() {
        super(name);
    }

    public Card getFirstCard() {
        return cards.getValues().get(0);
    }
}
