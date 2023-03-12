package domain.player;

import domain.score.Score;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_BOUNDARY = 16;


    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    public boolean isHittable() {
        return getScore()
                .isSmallerOrEqual(Score.from(DEALER_HIT_BOUNDARY));
    }
}
