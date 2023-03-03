package domain;

public class Dealer extends Player {
    private static final String DEALER_NAME = "딜러";
    public static final int STOP_LOWER_BOUND = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isDealerDraw() {
        return getScore() < STOP_LOWER_BOUND;
    }

    //public Outcome calculateOutcome(String playerName) {
    //    Player player = findPlayer(playerName);
    //    if (player.getScore() > getScore()) {
    //        return Outcome.WIN;
    //    }
    //    if (player.getScore() < getScore()) {
    //        return Outcome.LOSE;
    //    }
    //    return Outcome.DRAW;
    //}
}
