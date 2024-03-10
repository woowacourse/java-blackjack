package blackjack.domain.player;

import blackjack.domain.rule.DealerHitStrategy;
import blackjack.domain.rule.HitStrategy;
import blackjack.domain.rule.PlayerHitStrategy;

public enum ParticipantType {

    PLAYER(new PlayerHitStrategy()),
    DEALER(new DealerHitStrategy());

    private final HitStrategy hitStrategy;

    ParticipantType(HitStrategy hitStrategy) {
        this.hitStrategy = hitStrategy;
    }

    public HitStrategy getHitStrategy() {
        return hitStrategy;
    }
}
