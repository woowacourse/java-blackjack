package dto;

import domain.participant.Money;
import domain.participant.Player;

public record PlayerOutcomeDto(String name, String profit) {
    public static PlayerOutcomeDto from(Player player, Money profit) {
        return new PlayerOutcomeDto(
                player.getName(),
                String.valueOf(profit.getAmount())
        );
    }
}
