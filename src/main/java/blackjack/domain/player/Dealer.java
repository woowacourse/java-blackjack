package blackjack.domain.player;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int DEALER_ADD_CARD_LIMIT_SCORE = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean canAddCard() {
        return getTotal() <= DEALER_ADD_CARD_LIMIT_SCORE;
    }
}
