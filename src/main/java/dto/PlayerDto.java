package dto;

import domain.hand.Hand;
import domain.participant.Player;

public class PlayerDto {

    private final String name;
    private final Hand hand;

    public PlayerDto(Player player) {
        this.name = player.getName();
        this.hand = player.getHand();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
