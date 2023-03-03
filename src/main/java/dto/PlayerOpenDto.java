package dto;

import java.util.List;

import card.Card;
import player.Name;
import player.Player;

public class PlayerOpenDto {
    private final Name name;
    private final List<Card> cards;

    private PlayerOpenDto(Name name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerOpenDto from(Player player) {
        return new PlayerOpenDto(player.getName(), player.showCards());
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
