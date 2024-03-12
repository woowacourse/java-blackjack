package blackjack.dto;

import blackjack.domain.participants.Player;

public record BettingMoneyDto(String playerName, int money) {
    public static BettingMoneyDto from(Player player) {
        return new BettingMoneyDto(player.getName().getValue(), player.getMoney().getValue());
    }

}
