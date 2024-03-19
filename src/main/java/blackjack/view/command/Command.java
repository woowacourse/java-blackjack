package blackjack.view.command;

import java.util.Arrays;

public enum Command {
    YES("y"),
    NO("n");

    private final String text;

    Command(String value) {
        this.text = value;
    }

    public static Command fromText(final String text) {
        return Arrays.stream(values())
                .filter(value -> value.text.equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 키워드입니다."));
    }

    public boolean isYes() {
        return this == YES;
    }

    public String getText() {
        return text;
    }
}
