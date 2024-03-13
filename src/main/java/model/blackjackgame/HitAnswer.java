package model.blackjackgame;

public class HitAnswer {

    private static final String YES = "y";
    private static final String NO = "n";

    private final boolean isHit;

    public HitAnswer(boolean isHit) {
        this.isHit = isHit;
    }

    public static HitAnswer of(String isHitAnswer) {
        boolean isHit = convertYesOrNoToBoolean(isHitAnswer);
        return new HitAnswer(isHit);
    }

    private static boolean convertYesOrNoToBoolean(String answer) {
        if (YES.equals(answer)) {
            return true;
        }
        if (NO.equals(answer)) {
            return false;
        }
        throw new IllegalArgumentException("y 혹은 n 중 하나를 입력해 주세요.");
    }

    public boolean isHit() {
        return isHit;
    }
}
