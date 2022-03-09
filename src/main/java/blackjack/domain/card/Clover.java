package blackjack.domain.card;

public class Clover extends Card {

    private final String PATTERN = "클로버";

    public Clover(CardNumber cardNumber) {
        super(cardNumber);
    }

    @Override
    public String getCardPattern() {
        return PATTERN;
    }
}
