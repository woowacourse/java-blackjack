package blackjack.domain.card;

public class Heart extends Card {

    private final String PATTERN = "하트";

    public Heart(CardNumber cardNumber) {
        super(cardNumber);
    }

    @Override
    public String getCardPattern() {
        return PATTERN;
    }
}
