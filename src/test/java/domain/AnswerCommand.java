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
                .orElse(null);
    }
}
