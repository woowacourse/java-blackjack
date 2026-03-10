package domain.participant;

import domain.participant.dto.PlayerHandDto;
import domain.participant.dto.PlayerResultDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<PlayerName> playerNames) {
        return new Players(playerNames.stream()
                .map(Player::from)
                .toList()
        );
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    public Players giveInitialCardBundle(Dealer dealer) {
        players.forEach(dealer::handOutInitialCardToPlayer);
        return this;
    }

    public List<String> displayNames() {
        return players.stream()
                .map(Player::toDisplayMyName)
                .toList();
    }

    public List<PlayerHandDto> toPlayerHandDtos() {
        return players.stream()
                .map(PlayerHandDto::of)
                .toList();
    }

    public List<PlayerResultDto> toPlayerResultDtos() {
        return players.stream()
                .map(PlayerResultDto::from)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(players);
    }
}
