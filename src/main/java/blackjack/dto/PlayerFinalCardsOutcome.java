package blackjack.dto;

import blackjack.model.player.Player;

public record PlayerFinalCardsOutcome(String name, CardsOutcome cards, int score) {
    public static PlayerFinalCardsOutcome from(final Player player) {
        return new PlayerFinalCardsOutcome(
                player.getName().name(),
                CardsOutcome.from(player.getCards()),
                player.calculateCardsScore());
    }
}
