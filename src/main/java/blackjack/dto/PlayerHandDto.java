package blackjack.dto;

import blackjack.domain.gamer.Player;

public record PlayerHandDto(String name, HandDto playerHand) {

    public static PlayerHandDto fromPlayer(Player player) {
        String playerName = player.getName().value();
        HandDto playerHand = HandDto.fromHand(player.getHand());

        return new PlayerHandDto(playerName, playerHand);
    }
}
