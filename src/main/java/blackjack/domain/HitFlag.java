package blackjack.domain;

public enum HitFlag {
    Y("y"),
    N("n"),
    ;
    private static final String FLAG_INPUT_ERROR_MESSAGE = "예는 y, 아니오는 n을 입력해 주세요.";

    private final String flag;

    HitFlag(String flag) {
        this.flag = flag;
    }

    public static HitFlag fromCommand(String input) {
        validate(input);
        if (input.equalsIgnoreCase(Y.flag)) {
            return Y;
        }
        return N;
    }

    private static void validate(String input) {
        if (!input.equalsIgnoreCase(Y.flag) && !input.equalsIgnoreCase(N.flag)) {
            throw new IllegalArgumentException(FLAG_INPUT_ERROR_MESSAGE);
        }
    }
}
