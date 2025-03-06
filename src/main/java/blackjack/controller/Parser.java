package blackjack.controller;

import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import java.util.Arrays;

public class Parser {

    public static Participants parseParticipants(String names) {
        String[] splittedNames = names.split(",");
        validateNameCount(splittedNames);
        return new Participants(Arrays.stream(splittedNames)
                .map(Participant::new)
                .toList());
    }

    public static boolean parseCommand(String comamnd) {
        validateCommand(comamnd);
        return comamnd.equals("y");
    }

    private static void validateNameCount(String[] splittedNames) {
        if (splittedNames.length == 0) {
            throw new IllegalArgumentException("이름을 올바르게 입력해 주세요.");
        }
    }

    private static void validateCommand(String command) {
        if (!command.equals("y") && !command.equals("n")) {
            throw new IllegalArgumentException("y 또는 n을 입력해 주세요.");
        }
    }
}
