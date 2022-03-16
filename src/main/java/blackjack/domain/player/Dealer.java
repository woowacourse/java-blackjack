package blackjack.domain.player;

public class Dealer extends Player {

    private static final int ADD_CARD_CONDITION = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean acceptableCard() {
        return cards.calculateExpandScore() <= ADD_CARD_CONDITION;
    }
}
