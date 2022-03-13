package blackjack.dto;

import blackjack.model.player.Player;
import java.util.List;

public class DeckDTO {
    private final List<TrumpCardDTO> cards;

    private DeckDTO(List<TrumpCardDTO> cards) {
        this.cards = cards;
    }

    public static DeckDTO from(Player player) {
        return new DeckDTO(TrumpCardDTO.from(player.getDeck()));
    }

    public List<TrumpCardDTO> getCards() {
        return cards;
    }
}
