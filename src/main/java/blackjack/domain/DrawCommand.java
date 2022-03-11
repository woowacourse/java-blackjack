package blackjack.domain;

import java.util.Arrays;

public enum DrawCommand {
    YES("y"),
    NO("n");

    private final String inputValue;

    DrawCommand(String inputValue) {
        this.inputValue = inputValue;
    }

    public static DrawCommand from(String inputValue) {
        return Arrays.stream(values())
                .filter(drawCommand -> drawCommand.inputValue.equals(inputValue))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 지원하지 않는 커맨드입니다."));
    }
}
