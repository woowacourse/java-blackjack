package blackjack.controller;

import blackjack.model.player.PlayerName;

import java.util.Arrays;
import java.util.List;

public class Parser {

    public static final String NAME_DELIMITER = ",";
    public static final String ANSWER_YES = "y";
    public static final String ANSWER_NO = "n";

    public static List<PlayerName> parseNames(final String namesText) {
        String[] splittedNames = namesText.split(NAME_DELIMITER);
        validateNameCount(splittedNames);
        return Arrays.stream(splittedNames)
                .map(name -> new PlayerName(name.trim()))
                .toList();
    }

    private static void validateNameCount(final String[] splittedNames) {
        if (splittedNames.length == 0) {
            throw new IllegalArgumentException("이름을 올바르게 입력해 주세요.");
        }
    }

    public static boolean parseCommand(final String command) {
        validateCommand(command);
        return command.equalsIgnoreCase(ANSWER_YES);
    }

    private static void validateCommand(final String command) {
        if (command.equalsIgnoreCase(ANSWER_YES) || command.equalsIgnoreCase(ANSWER_NO)) {
            return;
        }
        throw new IllegalArgumentException("y 또는 n을 입력해 주세요.");
    }
}
