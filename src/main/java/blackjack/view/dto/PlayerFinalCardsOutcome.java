package blackjack.view.dto;

import blackjack.model.card.Card;
import blackjack.model.player.Player;
import java.util.List;

public record PlayerFinalCardsOutcome(String name, List<Card> cards, int totalScore) {
    public static PlayerFinalCardsOutcome of(final Player player) {
        return new PlayerFinalCardsOutcome(player.getName(), player.getHand().getCards(), player.calculateCardsTotal());
    }
}
