package blackjack.view;

import java.util.Arrays;

public enum Command {
    YES("y"),
    NO("n");
    private final String text;

    Command(String text) {
        this.text = text;
    }

    public static Command generate(String text) {
        return Arrays.stream(values())
                .filter(command -> command.getText().equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("명령어는 y 또는 n 이어야 한다."));
    }

    public String getText() {
        return text;
    }
}
