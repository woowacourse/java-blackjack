package dto;

import domain.bet.Betting;
import domain.hitStrategy.HitStrategy;
import domain.participants.Participant;
import domain.participants.Player;

public record PlayerCreateDto(
        String name,
        Betting betting
) {

    public Participant toDefaultStrategyPlayer(HitStrategy hitStrategy) {
        return new Player(name, betting, hitStrategy);
    }
}
