package blackjackgame.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum DrawRequest {
    YES("y"), NO("n");

    private final String value;

    DrawRequest(final String value) {
        this.value = value;
    }

    public static String message(final String playerName) {
        return String.format("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%s", System.lineSeparator() + playerName
            , YES.value, NO.value, System.lineSeparator());
    }

    public static Optional<DrawRequest> from(final String input) {
        return Arrays.stream(DrawRequest.values())
            .filter(drawRequest -> drawRequest.value.equalsIgnoreCase(input))
            .findFirst();
    }

    public static String getErrorPowerMsg() {
        List<String> values = Arrays.stream(DrawRequest.values())
            .map(DrawRequest::getValue)
            .collect(Collectors.toList());
        return String.join(",", values) + " 만 입력해주세요";
    }

    private String getValue() {
        return value;
    }
}
