package blackjack.dto;

import blackjack.model.card.Card;
import blackjack.model.player.Player;
import blackjack.model.player.PlayerName;
import java.util.List;

public record PlayerFinalCardsOutcome(PlayerName name, List<Card> cards, int totalScore) {
    public static PlayerFinalCardsOutcome from(final Player player) {
        return new PlayerFinalCardsOutcome(player.getName(), player.getCards(), player.calculateCardsTotalScore());
    }
}
