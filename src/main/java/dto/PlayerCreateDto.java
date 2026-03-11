package dto;

import domain.bet.Betting;
import domain.participants.Player;

public record PlayerCreateDto(
        String name,
        Betting betting
) {

    public Player toDefaultStrategyPlayer() {
        return Player.createDefaultStrategy(name, betting);
    }
}
