package blackjack.controller;

import blackjack.model.Participant;
import blackjack.model.Participants;
import java.util.Arrays;

public class Parser {

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
}
