package blackjack.domain.card;

public class Spade extends Card {

    private final String PATTERN = "스페이드";

    public Spade(CardNumber cardNumber) {
        super(cardNumber);
    }

    @Override
    public String getCardPattern() {
        return PATTERN;
    }
}
