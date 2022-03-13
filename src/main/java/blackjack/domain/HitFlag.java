package blackjack.domain;

public enum HitFlag {
    Y,
    N,
    ;
    private static final String HIT_FLAG = "y";
    private static final String STAND_FLAG = "n";
    private static final String FLAG_INPUT_ERROR_MESSAGE = "예는 y, 아니오는 n을 입력해 주세요.";

    public static HitFlag fromCommand(String input) {
        validate(input);
        if (input.equals(HIT_FLAG)) {
            return HitFlag.Y;
        }
        return HitFlag.N;
    }

    private static void validate(String input) {
        if (!input.equals(HIT_FLAG) && !input.equals(STAND_FLAG)) {
            throw new IllegalArgumentException(FLAG_INPUT_ERROR_MESSAGE);
        }
    }
}
