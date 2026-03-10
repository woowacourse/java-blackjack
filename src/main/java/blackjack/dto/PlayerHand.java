package blackjack.dto;

import blackjack.domain.participant.Player;
import java.util.List;

public record PlayerHand(
        String nickname,
        List<String> cardNames
) {
    public static PlayerHand from(Player player) {
        return new PlayerHand(
                player.getNickname(),
                player.getCards()
        );
    }

    public static List<PlayerHand> listOf(List<Player> players) {
        return players.stream()
                .map(PlayerHand::from)
                .toList();
    }
}
