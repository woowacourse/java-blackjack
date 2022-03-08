package blackjack.domain;

public class HitOrStand {

    private static final String HIT_FLAG = "y";
    private static final String STAND_FLAG = "n";
    private static final String FLAG_INPUT_ERROR_MESSAGE = "예는 y, 아니오는 n을 입력해 주세요.";

    private static final HitOrStand hit = new HitOrStand(true);
    private static final HitOrStand stand = new HitOrStand(false);

    private final boolean value;

    private HitOrStand(boolean value) {
        this.value = value;
    }

    public static HitOrStand valueOf(String input) {
        validate(input);
        if (input.equals(HIT_FLAG)) {
            return hit;
        }
        return stand;
    }

    private static void validate(String input) {
        if (!input.equals(HIT_FLAG) && !input.equals(STAND_FLAG)) {
            throw new IllegalArgumentException(FLAG_INPUT_ERROR_MESSAGE);
        }
    }

    public boolean isValue() {
        return value;
    }
}
