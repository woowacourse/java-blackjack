package common;

import domain.card.Card;
import domain.gamer.Player;

import java.util.List;

public class PlayerDto {
    private String name;
    private List<Card> cards;

    public PlayerDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerDto of(Player player) {
        List<Card> cards = player.getPlayingCards().getCards();
        return new PlayerDto(player.getName(), cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
