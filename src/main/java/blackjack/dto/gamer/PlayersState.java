package blackjack.dto.gamer;

import blackjack.domain.gamer.Player;

import java.util.List;

public record PlayersState(List<PlayerState> infos) {

    public static PlayersState from(final List<Player> players) {
        return new PlayersState(players.stream()
                .map(PlayerState::from)
                .toList());
    }
}
