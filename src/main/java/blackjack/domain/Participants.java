package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final Dealer dealer = new Dealer();

    private final List<Participant> participants;

    public Participants(List<String> names) {
        this.participants = names.stream()
                .map(User::new)
                .collect(Collectors.toList());
        this.participants.add(dealer);
    }
}
