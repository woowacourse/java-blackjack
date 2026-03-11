package blackjack.view.dto;

import blackjack.domain.participant.Player;
import java.util.List;

public record PlayerNames(
        List<String> playerNames
) {
    
    public static PlayerNames from(List<Player> players) {
        return new PlayerNames(
                players.stream()
                        .map(Player::getNickname)
                        .toList()
        );
    }
}
