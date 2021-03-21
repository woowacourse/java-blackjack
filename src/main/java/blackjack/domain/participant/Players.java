package blackjack.domain.participant;

import blackjack.controller.dto.PlayerRequestDto;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players createPlayers(final List<Player> players) {
        return new Players(players);
    }

    public static Players valueOf(final List<PlayerRequestDto> playerRequestDtos) {
        return new Players(playerRequestDtos.stream()
                .map(PlayerRequestDto::toEntity)
                .collect(Collectors.toList()));
    }

    public int size() {
        return players.size();
    }

    public Player get(int index) {
        return players.get(index);
    }

    public String indexOfName(int index) {
        return this.get(index).getName();
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    public <R> Stream<R> map(Function<? super Player, ? extends R> function) {
        return this.players.stream().map(function);
    }

    public List<Player> toList() {
        return Collections.unmodifiableList(this.players);
    }
}
