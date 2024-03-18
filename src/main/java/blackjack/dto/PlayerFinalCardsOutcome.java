package blackjack.dto;

import blackjack.model.player.Player;
import blackjack.vo.Card;
import blackjack.vo.PlayerName;
import java.util.List;

public record PlayerFinalCardsOutcome(PlayerName name, List<Card> cards, int score) {
    public static PlayerFinalCardsOutcome from(final Player player) {
        return new PlayerFinalCardsOutcome(player.getName(), player.getCards(), player.calculateCardsScore());
    }
}
