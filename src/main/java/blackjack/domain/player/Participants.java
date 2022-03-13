package blackjack.domain.player;

import blackjack.domain.player.Participant;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants implements Iterable<Participant> {

    private final List<Participant> participants;

    public Participants(final List<String> names) {
        checkDuplicatedNames(names);

        participants = names.stream()
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    private void checkDuplicatedNames(final List<String> names) {
        int count = (int) names.stream()
                .distinct()
                .count();

        if (count != names.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자의 이름은 중복될 수 없습니다.");
        }
    }

    @Override
    public Iterator<Participant> iterator() {
        return participants.iterator();
    }
}
