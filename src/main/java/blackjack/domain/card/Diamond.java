package blackjack.domain.card;

public class Diamond extends Card {

    private final String PATTERN = "다이아몬드";

    public Diamond(CardNumber cardNumber) {
        super(cardNumber);
    }

    @Override
    public String getCardPattern() {
        return PATTERN;
    }
}
