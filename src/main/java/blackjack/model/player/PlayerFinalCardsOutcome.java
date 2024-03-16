package blackjack.model.player;

import blackjack.model.card.Card;
import java.util.List;

public record PlayerFinalCardsOutcome(String name, List<Card> cards, int totalScore) {
    public static PlayerFinalCardsOutcome from(final Player player) {
        return new PlayerFinalCardsOutcome(player.getName(), player.getCards(), player.calculateCardsTotalScore());
    }
}
