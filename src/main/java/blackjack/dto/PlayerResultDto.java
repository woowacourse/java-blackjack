package blackjack.dto;

import blackjack.model.money.Money;
import blackjack.model.participant.Player;

public record PlayerResultDto(String name, Money profit) {

    public static PlayerResultDto from(Player player, Money profit) {
        return new PlayerResultDto(player.getName(), profit);
    }
}
