package domain.player;

import domain.dealer.Dealer;
import domain.player.dto.PlayerHandDto;
import domain.player.dto.PlayerResultDto;

import java.util.List;
import java.util.stream.Stream;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    public void giveInitialCardBundle(Dealer dealer) {
        players.forEach(dealer::handOutInitialCardToPlayer);
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

}
