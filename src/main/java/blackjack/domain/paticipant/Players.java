package blackjack.domain.paticipant;

import java.util.List;
import java.util.Objects;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        Objects.requireNonNull(players, "players는 null이 들어올 수 없습니다.");
        this.players = players;
    }
}
