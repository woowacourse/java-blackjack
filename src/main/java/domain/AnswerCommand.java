package domain;

import java.util.Arrays;
import java.util.Objects;

public enum AnswerCommand {
    YES("y"),
    NO("n");

    private final String command;

    AnswerCommand(final String command) {
        this.command = command;
    }

    public static AnswerCommand findByAnswer(final String command) {
        return Arrays.stream(AnswerCommand.values())
                .filter(answerCommand -> Objects.equals(answerCommand.command, command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 커멘드 입니다."));
    }

    public boolean isYes() {
        return Objects.equals(AnswerCommand.YES, this);

    }
}
