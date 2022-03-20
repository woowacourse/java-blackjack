package blackjack.domain.player;

import blackjack.domain.result.Result;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants implements Iterable<Participant> {

    private static final int MIN_PLAYER_NUMBER = 1;
    private static final int MAX_PLAYER_NUMBER = 8;
    private final Map<Participant, Money> participants;

    public Participants(final List<String> names) {
        checkNonNull(names);
        checkNumberOfPlayer(names);
        checkDuplicatedNames(names);

        participants = names.stream()
                .collect(Collectors.toMap(Participant::new, money -> new Money(0),  (o1, o2) -> o1, LinkedHashMap::new));
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

    public void putBet(final Participant participant, final int bet) {
        participants.computeIfPresent(participant, (key, value) -> new Money(bet));
    }

    public int getRevenue(Participant participant, Result result) {
        Money bet = participants.get(participant);
        return bet.getRevenue(result);
    }

    @Override
    public Iterator<Participant> iterator() {
        return participants.keySet().iterator();
    }
}
