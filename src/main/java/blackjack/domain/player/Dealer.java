package blackjack.domain.player;

public class Dealer extends Player {

    private static final int DEALER_GET_CARD_UPPER_BOUND = 17;

    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean isNotFinished() {
        return playingCards.getCardSum() < DEALER_GET_CARD_UPPER_BOUND;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
