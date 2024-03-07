package model.blackjackgame;

public class HitAnswer {

    private static final String YES = "y";
    private static final String NO = "n";
    private static final String INVALID_ANSWER = "y 혹은 n 중 하나를 입력해 주세요.";

    private final boolean isHit;

    public HitAnswer(boolean isHit) {
        this.isHit = isHit;
    }

    public static HitAnswer of(String isHitAnswer) {
        boolean isHit = convertYesOrNoToBoolean(isHitAnswer);
        return new HitAnswer(isHit);
    }

    private static boolean convertYesOrNoToBoolean(String answer) {
        if (answer.equals(YES)) {
            return true;
        }
        if (answer.equals(NO)) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_ANSWER);
    }

    public boolean isStay() {
        return !isHit;
    }
}
