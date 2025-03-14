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

    public boolean isNotUp() {
        return getScore() < STANDING_CONDITION;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
