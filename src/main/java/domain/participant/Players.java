package domain.participant;

import java.util.List;

public class Players {

    private final List<Player> value;


    public Players(List<Player> players) {
        validate(players);
        this.value = players;
    }

    private static final int MIN_PARTICIPANT_COUNT = 2;
    private static final int MAX_PARTICIPANT_COUNT = 8;

    private void validate(List<Player> players) {
        checkParticipantCount(players);
        checkDuplicateParticipant(players);
    }

    private void checkDuplicateParticipant(List<Player> players) {
        long distinctNamesCount = players.stream()
                .distinct()
                .count();

        if (distinctNamesCount != players.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    private void checkParticipantCount(List<Player> players) {
        if (players.size() < MIN_PARTICIPANT_COUNT || players.size() > MAX_PARTICIPANT_COUNT) {
            throw new IllegalArgumentException(
                    String.format("최소 %d명 최대 %d명까지 입력받을 수 있습니다.", MIN_PARTICIPANT_COUNT, MAX_PARTICIPANT_COUNT));
        }
    }

    public List<Name> getNames() {
        return value.stream()
                .map(Participant::getName)
                .toList();
    }

    public List<Player> getValue() {
        return value;
    }
}
