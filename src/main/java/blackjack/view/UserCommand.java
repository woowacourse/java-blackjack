package blackjack.view;

import java.util.Arrays;

public enum UserCommand {
    YES("y"),
    NO("n");
    
    private final String command;
    
    UserCommand(String command) {
        this.command = command;
    }
    
    public static UserCommand from(String input) {
        return Arrays.stream(values())
                .filter(userCommand -> userCommand.command.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효한 커맨드(예는 y, 아니오는 n)를 입력해 주세요."));
    }
}
