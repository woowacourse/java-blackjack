package blackjack.domain.factory;

import blackjack.domain.player.Names;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.List;
import java.util.stream.Collectors;

public class PlayersFactory {

    public static Players of(Names names) {
        List<Player> players = names.getNames().stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
        return new Players(players);
    }
}
