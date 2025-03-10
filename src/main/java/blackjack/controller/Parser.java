package blackjack.controller;

import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import java.util.Arrays;

public class Parser {

    private final static String YES_COMMAND = "y";
    private static final String NO_COMMAND = "n";

    public static Participants parseParticipants(String names) {
        String[] splittedNames = names.split(",");
        validateNameCount(splittedNames);
        return new Participants(Arrays.stream(splittedNames)
                .map(Participant::new)
                .toList());
    }

    private static void validateNameCount(String[] splittedNames) {
        if (splittedNames.length == 0) {
            throw new IllegalArgumentException("이름을 올바르게 입력해 주세요.");
        }
    }

    public static boolean parseCommand(String comamnd) {
        validateCommand(comamnd);
        return comamnd.equals(YES_COMMAND);
    }

    private static void validateCommand(String command) {
        if (!(command.equals(YES_COMMAND) || command.equals(NO_COMMAND))) {
            throw new IllegalArgumentException("y 또는 n을 입력해 주세요.");
        }
    }
}
