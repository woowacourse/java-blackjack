package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.ScoreResult;

public class Players {

    private static final int CAPACITY = 8;

    private final List<Player> players;

    public Players(List<Player> players) {
        validateCapacity(players);
        this.players = new ArrayList<>(players);
    }

    private void validateCapacity(List<Player> players) {
        if (players.size() > CAPACITY) {
            throw new IllegalArgumentException("인원수는 8명을 넘을 수 없습니다.");
        }
    }

    public List<Player> getValue() {
        return List.copyOf(players);
    }

    public Players copy() {
        return new Players(List.copyOf(players.stream()
            .map(Player::copy)
            .collect(Collectors.toList())));
    }

    public ScoreResult compete(Dealer dealer) {
        return ScoreResult.from(List.copyOf(players), dealer);
    }
}
