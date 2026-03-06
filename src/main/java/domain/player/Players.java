package domain.player;

import domain.dealer.Dealer;
import domain.player.dto.PlayerHandDto;

import java.util.List;
import java.util.stream.Stream;

public class Players {

    private List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public void dealCardBundle(Dealer dealer) {
        for (Player player : players) {
            dealer.handOutCardToPlayer(player, 2);
        }
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    public List<PlayerHandDto> getPlayersHand() {
        return players.stream()
                .map(Player::getMyHands)
                .toList();
    }

    public List<String> displayNames() {
        return players.stream()
                .map(Player::toDisplayMyName)
                .toList();
    }

}
