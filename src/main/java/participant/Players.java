package participant;

import java.util.List;

public class Players {

    private static final int AVAILABLE_PARTICIPANT_MIN_COUNT = 1;
    private static final int AVAILABLE_PARTICIPANT_MAX_COUNT = 30;
    private final List<Player> values;

    public Players(List<Player> values) {
        validateDuplication(values);
        validateNumber(values);
        this.values = values;
    }

    private static void validateDuplication(List<Player> values) {
        if (values.stream().distinct().count() != values.size()) {
            throw new IllegalArgumentException("중복된 플레이어는 등록할 수 없습니다.");
        }
    }

    private static void validateNumber(List<Player> values) {
        if (values.size() < AVAILABLE_PARTICIPANT_MIN_COUNT || values.size() > AVAILABLE_PARTICIPANT_MAX_COUNT) {
            throw new IllegalArgumentException("게임 참가자는 1~30명까지 가능합니다.");
        }
    }

    public List<Player> getPlayers() {
        return values;
    }

}
