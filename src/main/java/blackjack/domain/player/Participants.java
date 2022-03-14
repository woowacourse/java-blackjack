package blackjack.domain.player;

import static blackjack.domain.Rule.MAX_PARTICIPANTS_SIZE;
import static blackjack.domain.Rule.MIN_PARTICIPANTS_SIZE;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants implements Iterable<Participant> {

    private final List<Participant> participants;

    public Participants(final List<String> names) {
        checkSize(names);
        checkDuplicatedNames(names);

        participants = names.stream()
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    private void checkSize(final List<String> names) {
        final int size = names.size();
        if (size < MIN_PARTICIPANTS_SIZE.getValue() || size > MAX_PARTICIPANTS_SIZE.getValue()) {
            throw new IllegalArgumentException("[ERROR] 참자가 인원은 2명~8명 사이여야합니다.");
        }
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
