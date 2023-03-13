package domain.game;

import java.util.Arrays;

public enum AddCardCommand {
    YES("y"),
    NO("n");
    
    private final String command;
    
    AddCardCommand(String command) {
        this.command = command;
    }
    
    public static AddCardCommand valueOfCommand(String command) {
        return Arrays.stream(values())
                .filter(addCardCommand -> addCardCommand.isSameCommand(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("카드를 더 받는 여부의 입력은 y 또는 n 만 입력할 수 있습니다."));
    }
    
    private boolean isSameCommand(String command) {
        return this.command.equals(command);
    }
    
    public boolean isAddCardCommand() {
        return this == YES;
    }
    
    public boolean isNotAddCardCommand() {
        return this == NO;
    }
}
