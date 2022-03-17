package blackjack.dto;

import blackjack.model.player.Player;
import java.util.List;

public final class HandDTO {
    private final List<TrumpCardDTO> cards;

    private HandDTO(List<TrumpCardDTO> cards) {
        this.cards = cards;
    }

    public static HandDTO from(Player player) {
        return new HandDTO(TrumpCardDTO.from(player.getHand()));
    }

    public List<TrumpCardDTO> getCards() {
        return cards;
    }
}
