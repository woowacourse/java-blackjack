package blackjack;

import java.util.Arrays;
import java.util.NoSuchElementException;

enum Command {

    YES("y"),
    NO("n");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command from(String command) {
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 명령어입니다."));
    }

    public boolean isYes() {
        return this.equals(YES);
    }

    public boolean isNo() {
        return this.equals(NO);
    }
}
