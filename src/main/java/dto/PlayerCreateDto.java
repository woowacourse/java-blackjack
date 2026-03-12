package dto;

import domain.bet.Betting;
import domain.card.Hand;
import domain.participants.Player;

public record PlayerCreateDto(
        String name,
        Betting betting
) {

    public Player toDefaultStrategyPlayer(Hand hand) {
        return Player.createDefaultStrategy(name, hand, betting);
    }
}
