package blackjack.model.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {
    private static final int MIN_PARTICIPANT = 2;
    private static final int MAX_PARTICIPANT = 8;

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = new ArrayList<>(players);
    }

    public void validate(List<Player> players) {
        validateParticipantCount(players);
        validateDuplicateName(players);
    }

    private void validateParticipantCount(List<Player> players) {
        if (players.size() < MIN_PARTICIPANT || players.size() > MAX_PARTICIPANT) {
            throw new IllegalArgumentException("참여자는 2~8명 이여야 합니다.");
        }
    }

    private void validateDuplicateName(List<Player> players) {
        Set<Player> uniquePlayers = new HashSet<>(players);
        if (uniquePlayers.size() != players.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    private static void validateNameCount(String[] splitName) {
        if (splitName.length == 0) {
            throw new IllegalArgumentException("이름을 올바르게 입력해 주세요.");
        }
    }


    public List<Player> getParticipants() {
        return new ArrayList<>(players);
    }
}
