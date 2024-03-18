package blackjack.view.dto;

import blackjack.model.player.Player;

public record PlayerFinalCardsOutcome(String name, String cards, int totalScore) {
    public static PlayerFinalCardsOutcome of(final Player player) {
        return new PlayerFinalCardsOutcome(
                player.getName().toString(), player.getCards().toString(),
                player.calculateCardsTotalScore().getValue()
        );
    }
}
