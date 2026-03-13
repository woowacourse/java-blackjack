package dto;

import domain.bet.Betting;
import domain.card.Hand;
import domain.hitStrategy.HitStrategy;
import domain.participants.Participant;
import domain.participants.Player;

public record PlayerCreateDto(
        String name,
        Betting betting
) {

    public Participant toDefaultStrategyPlayer(Hand hand, HitStrategy hitStrategy) {
        return new Player(name, hand, betting, hitStrategy);
    }
}
