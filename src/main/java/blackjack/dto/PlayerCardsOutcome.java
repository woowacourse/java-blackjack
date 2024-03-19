package blackjack.dto;

import blackjack.model.player.Player;

public record PlayerCardsOutcome(String name, CardsOutcome cards) {
    public static PlayerCardsOutcome from(final Player player) {
        return new PlayerCardsOutcome(player.getName().name(), CardsOutcome.from(player.getCards()));
    }
}
