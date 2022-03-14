package dto;

import domain.card.Card;
import domain.player.Player;
import java.util.List;

public class OpenCardsDto {
    private final String name;
    private final List<Card> cards;

    private OpenCardsDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = List.copyOf(cards);
    }

    public static OpenCardsDto from(Player player) {
        return new OpenCardsDto(player.getName(), player.getOpenCards());
    }

    public String getName() {
        return name;
    }

    public List<Card> getInitialOpenCards() {
        return cards;
    }
}
