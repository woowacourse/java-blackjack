package blackjack.domain.player;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants implements Iterable<Participant> {

    public static final int MIN_PLAYER_NUMBER = 1;
    public static final int MAX_PLAYER_NUMBER = 8;
    private final List<Participant> participants;

    public Participants(final List<String> names) {
        checkNonNull(names);
        checkNumberOfPlayer(names);
        checkDuplicatedNames(names);

        participants = names.stream()
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    private void checkNonNull(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 한 명 이상의 참가자를 입력해주세요.");
        }
    }

    private void checkNumberOfPlayer(List<String> names) {
        int numberOfPlayer = names.size();

        if (numberOfPlayer < MIN_PLAYER_NUMBER || numberOfPlayer > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 참가자는 1명 이상, 8명 이하여야합니다.");
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
