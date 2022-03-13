package blackjack.domain.strategy;

import blackjack.domain.HitFlag;
import blackjack.domain.player.Player;

public interface HitStrategy {
    HitFlag getHitFlag(Player player);
}
