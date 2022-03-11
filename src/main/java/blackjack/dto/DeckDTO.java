package blackjack.dto;

import blackjack.model.player.Player;
import java.util.List;

public class DeckDTO {
    private List<String> cards;

    private DeckDTO(List<String> cards) {
        this.cards = cards;
    }

    public static DeckDTO from(Player player) {
        return new DeckDTO(player.getDeckToString());
    }

    public List<String> getCards() {
        return cards;
    }
}
