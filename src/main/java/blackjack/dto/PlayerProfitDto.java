package blackjack.dto;

import blackjack.model.money.Money;
import blackjack.model.participant.Player;

public record PlayerProfitDto(String name, Money profit) {

    public static PlayerProfitDto from(final Player player, final Money profit) {
        return new PlayerProfitDto(player.getName(), profit);
    }
}
