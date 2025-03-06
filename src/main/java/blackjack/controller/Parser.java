package blackjack.controller;

import blackjack.model.Participant;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static List<Participant> parseParticipants(String names) {
        String[] splittedNames = names.split(",");
        validateNameCount(splittedNames);
        return Arrays.stream(splittedNames)
                .map(Participant::new)
                .toList();
    }

    private static void validateNameCount(String[] splittedNames) {
        if (splittedNames.length == 0) {
            throw new IllegalArgumentException("이름을 올바르게 입력해 주세요.");
        }
    }
}
