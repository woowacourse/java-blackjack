package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private final List<Participant> participants;

    public Participants(List<String> names) {
        participants = names.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }
}
