package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record Players(List<Player> players) {
    public String getJoinedNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public List<Player> players() {
        return new ArrayList<>(this.players);
    }
}
