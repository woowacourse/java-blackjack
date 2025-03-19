package model.participant;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int STANDING_CONDITION = 17;

    private final String nickname;

    private Dealer(String nickname) {
        this.nickname = nickname;
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return ableToDraw();
    }

    @Override
    public boolean ableToDraw() {
        return getScore() < STANDING_CONDITION;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
