package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {
    List<Participant> participants = new ArrayList<>();

    public Participants(List<String> names, Hand hand) {
        validateDuplicatedName(names);
        validatePlayerCount(names);

        preparePlayers(names, hand);
        prepareDealer(hand);
    }

    private void validatePlayerCount(List<String> names) {
        if (names.size() > 5) {
            throw new IllegalArgumentException();
        }
    }

    private void preparePlayers(List<String> names, Hand hand) {
        for (String name : names) {
            Player player = new Player(name, hand);
            participants.add(player);
        }
    }

    private void prepareDealer(Hand hand) {
        Participant dealer = new Dealer(hand);
        participants.add(dealer);
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> namesSet = new HashSet<>(names);

        if (namesSet.size() != names.size()) {
            throw new IllegalArgumentException();
        }
    }
}
