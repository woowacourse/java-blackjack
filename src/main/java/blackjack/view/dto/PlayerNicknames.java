package blackjack.view.dto;

import blackjack.domain.participant.Player;
import java.util.List;

public record PlayerNicknames(List<String> nicknames) {

    public static PlayerNicknames from(List<Player> players) {
        List<String> playerNicknames = players.stream()
                .map(Player::getNickname)
                .toList();
        return new PlayerNicknames(playerNicknames);
    }
}
