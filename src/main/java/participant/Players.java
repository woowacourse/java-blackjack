package participant;

import java.util.List;

public class Players {

    private final List<Player> values;

    private Players(List<Player> values) {
        validateDuplication(values);
        validateNumber(values);
        this.values = values;
    }

    public static Players from(List<String> inputs) {
        List<Player> inputPlayers = inputs.stream()
                .map(Player::new)
                .toList();
        return new Players(inputPlayers);
    }

    private static void validateDuplication(List<Player> values) {
        if (values.stream().distinct().count() != values.size()) {
            throw new IllegalArgumentException("중복된 플레이어는 등록할 수 없습니다.");
        }
    }

    private static void validateNumber(List<Player> values) {
        if (values.size() < 1 || values.size() > 30) {
            throw new IllegalArgumentException("게임 참가자는 1~30명까지 가능합니다.");
        }
    }

    public List<Player> getPlayers() {
        return values;
    }

}
