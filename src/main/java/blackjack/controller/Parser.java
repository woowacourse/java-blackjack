package blackjack.controller;

import blackjack.model.Participant;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static List<Participant> parseParticipants(String names) {
        String[] splittedNames = names.split(",");
        return Arrays.stream(splittedNames)
                .map(Participant::new)
                .toList();
    }
}
