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

    public static AddCardRequest validate(final String input) {
        return Arrays.stream(AddCardRequest.values())
            .filter(cardResponse -> cardResponse.value.equalsIgnoreCase(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(getErrorRequestMsg()));
    }

    private static String getErrorRequestMsg() {
        List<String> values = Arrays.stream(AddCardRequest.values())
            .map(AddCardRequest::value)
            .collect(Collectors.toList());
        return String.join(",", values) + " 만 입력해주세요";
    }

    public String value() {
        return value;
    }
}
