package blackjack.view;

import java.util.Arrays;

public enum PlayCommand {

    YES("y"),
    NO("n"),
    ;

    private final String value;

    PlayCommand(String value) {
        this.value = value;
    }

    public static PlayCommand of(String inputCommand) {
        return Arrays.stream(values())
                .filter(playCommand -> isEqualsValue(inputCommand, playCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어 입니다."));
    }

    private static boolean isEqualsValue(String inputCommand, PlayCommand playCommand) {
        return playCommand.value.equals(inputCommand);
    }

    public boolean isYes() {
        return this == YES;
    }


    public boolean isNo() {
        return this == NO;
    }
}
