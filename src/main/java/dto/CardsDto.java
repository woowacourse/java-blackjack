package dto;

import domain.card.Card;
import domain.player.Player;
import java.util.List;

public class CardsDto {
    private final String name;
    private final List<Card> cards;

    private CardsDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = List.copyOf(cards);
    }

    public static CardsDto from(Player player) {
        return new CardsDto(player.getName(), player.getOpenCards());
    }

    static CardsDto forResult(Player player) {
        return new CardsDto(player.getName(), player.getCards());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
