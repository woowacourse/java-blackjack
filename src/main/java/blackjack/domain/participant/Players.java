package blackjack.domain.participant;

import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayersResults;

import java.util.Collections;
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


    public PlayersResults createPlayerResults(Dealer dealer) {
        List<PlayerResult> playerResults = players.stream()
                .map(player -> player.createPlayerResult(dealer))
                .collect(Collectors.toList());
        return new PlayersResults(playerResults);
    }

    public List<String> names() {
        return players.stream()
                .map(Player::name)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
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
