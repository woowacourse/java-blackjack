package blackjack.domain.participant;

public class Dealer extends User {

    public static final int DEALER_STAND_SCORE = 17;

    public Dealer() {
        super("딜러");
    }

    public boolean canHit() {
        return getScore() < DEALER_STAND_SCORE;
    }
}

