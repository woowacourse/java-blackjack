package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {
    List<Participant> participants = new ArrayList<>();

    public Participants(List<String> names) {
        validateDuplicatedName(names);
        validatePlayerCount(names);

        preparePlayers(names);
        prepareDealer();
    }

    private void validatePlayerCount(List<String> names) {
        if (names.size() > 5) {
            throw new IllegalArgumentException();
        }
    }

    private void preparePlayers(List<String> names) {
        names.stream()
                .map(Player::new)
                .forEach(participant -> participants.add(participant));
    }

    private void prepareDealer() {
        Participant dealer = new Dealer();
        participants.add(dealer);
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> namesSet = new HashSet<>(names);

        if (namesSet.size() != names.size()) {
            throw new IllegalArgumentException();
        }
    }
}
