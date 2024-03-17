package blackjack.model.player;

import blackjack.model.card.Card;
import java.util.List;

public record PlayerCardsOutcome(String name, List<Card> cards) {
    public static PlayerCardsOutcome from(final Player player) {
        return new PlayerCardsOutcome(player.getName(), player.getCards());
    }
}
