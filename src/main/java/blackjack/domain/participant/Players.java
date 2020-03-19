package blackjack.domain.participant;

import blackjack.domain.result.PlayerResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static blackjack.domain.participant.PlayersFactory.NULL_ARGUMENT_ERR_MSG;

public class Players {
    private final List<Player> players;

    public Players(List<Name> names) {
        Objects.requireNonNull(names, NULL_ARGUMENT_ERR_MSG);
        this.players = PlayersFactory.of(names);
    }

    public Players(List<Name> names, List<Money> moneys) {
        Objects.requireNonNull(names, NULL_ARGUMENT_ERR_MSG);
        Objects.requireNonNull(moneys, NULL_ARGUMENT_ERR_MSG);
        this.players = PlayersFactory.of(names, moneys);
    }


    public List<PlayerResult> createPlayerResults(Dealer dealer) {
        return players.stream()
                .map(player -> player.createPlayerResult(dealer))
                .collect(Collectors.toList());
    }

    public List<String> names() {
        return players.stream()
                .map(Player::name)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
