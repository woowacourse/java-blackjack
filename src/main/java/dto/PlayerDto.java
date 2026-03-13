package dto;

import domain.card.Card;

import java.util.List;

public class PlayerDto {

    private final String name;
    private final List<Card> playerHand;

    public PlayerDto(String name, List<Card> playerHand) {
        this.name = name;
        this.playerHand = playerHand;
    }

    public String getName() {
        return name;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }
}
