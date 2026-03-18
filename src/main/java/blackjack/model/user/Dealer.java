package blackjack.model.user;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_STAND = 17;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean isHitAvailable() {
        return totalScore() < DEALER_ADD_CARD_STAND;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }
}
