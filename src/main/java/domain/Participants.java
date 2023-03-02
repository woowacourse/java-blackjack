package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final int MIN_SIZE_EXCLUSIVE = 2;
    private static final int MAX_SIZE_EXCLUSIVE = 8;

    private final List<Participant> participants;

    public Participants(List<String> names) {
        Cards cards = new Cards(new RandomCardsShuffler());
        this.participants = names.stream()
                .map(Name::new)
                .map(name -> new Participant(name, cards.getInitialCards()))
                .collect(Collectors.toList());
        participants.add(0, new Dealer(cards.getInitialCards()));
        validateSize(names);
    }

    private void validateSize(final List<String> names) {
        if (participants.size() < MIN_SIZE_EXCLUSIVE || participants.size() > MAX_SIZE_EXCLUSIVE) {
            throw new IllegalArgumentException("전체 참가자 수는 2명 이상 8명 이하여야 합니다!");
        }
    }

    public List<String> getNames() {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
