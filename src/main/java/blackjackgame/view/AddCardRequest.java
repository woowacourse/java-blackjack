package blackjackgame.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AddCardRequest {
    YES("y"), NO("n");

    private final String value;

    AddCardRequest(final String value) {
        this.value = value;
    }

    public static String printAddCardRequest(final String playerName) {
        return String.format("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%s", System.lineSeparator() + playerName
            , YES.value, NO.value, System.lineSeparator());
    }

    public static AddCardRequest validate(final String input) {
        return Arrays.stream(AddCardRequest.values())
            .filter(cardResponse -> cardResponse.value.equalsIgnoreCase(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(getErrorRequestMsg()));
    }

    private static String getErrorRequestMsg() {
        List<String> values = Arrays.stream(AddCardRequest.values())
            .map(AddCardRequest::getValue)
            .collect(Collectors.toList());
        return String.join(",", values) + " 만 입력해주세요";
    }

    private String getValue() {
        return value;
    }
}
