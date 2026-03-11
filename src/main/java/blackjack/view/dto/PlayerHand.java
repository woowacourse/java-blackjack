package blackjack.view.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.List;

public record PlayerHand(
        String nickname,
        List<String> cardNames
) {
    
    public static PlayerHand from(Player player) {
        return new PlayerHand(
                player.getNickname(),
                toCardDisplayNames(player)
        );
    }
    
    public static List<PlayerHand> listOf(List<Player> players) {
        return players.stream()
                .map(PlayerHand::from)
                .toList();
    }
    
    private static List<String> toCardDisplayNames(Participant participant) {
        return participant.getCards().stream()
                .map(CardDisplayName::from)
                .map(CardDisplayName::displayName)
                .toList();
    }
}
