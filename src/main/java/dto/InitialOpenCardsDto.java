package dto;

import domain.card.Card;
import domain.player.Player;
import java.util.List;

public class InitialOpenCardsDto {
    private final String name;
    private final List<Card> cards;

    private InitialOpenCardsDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = List.copyOf(cards);
    }

    public static InitialOpenCardsDto from(Player player) {
        return new InitialOpenCardsDto(player.getName(), player.getInitialOpenCards());
    }

    public String getName() {
        return name;
    }

    public List<Card> getInitialOpenCards() {
        return cards;
    }
}
