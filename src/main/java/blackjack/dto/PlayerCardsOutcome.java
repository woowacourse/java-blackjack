package blackjack.dto;

import blackjack.model.card.Card;
import blackjack.model.player.Player;
import blackjack.model.player.PlayerName;
import java.util.List;

public record PlayerCardsOutcome(PlayerName name, List<Card> cards) {
    public static PlayerCardsOutcome from(final Player player) {
        return new PlayerCardsOutcome(player.getName(), player.getCards());
    }
}
