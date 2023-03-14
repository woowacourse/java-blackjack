package domain.participant;

import java.util.Arrays;

public enum ReceiveValidate {
    YES("y", true),
    NO("n", false);

    private static final String INPUT_DOES_NOT_Y_OR_N = "[ERROR] 추가 카드 요청 여부는 y 또는 n을 입력해 주세요.";

    final String value;
    final boolean isReceivable;

    ReceiveValidate(String value, boolean isReceivable) {
        this.value = value;
        this.isReceivable = isReceivable;
    }

    public static boolean checkReceivable(String input) {
        return Arrays.stream(values())
                .filter(card -> card.value.equals(input))
                .map(card -> card.isReceivable)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INPUT_DOES_NOT_Y_OR_N));
    }
}
