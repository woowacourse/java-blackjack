package blackjack.view.object;

import java.util.Arrays;

public enum Command {
    HIT("y"),
    STAND("n");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command convertInputToCommand(String input) {
        return Arrays.stream(values())
                .filter(command -> command.name.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력과 일치하는 명령어가 존재하지 않습니다."));
    }

    public static boolean isHit(Command command) {
        return command == HIT;
    }
}
