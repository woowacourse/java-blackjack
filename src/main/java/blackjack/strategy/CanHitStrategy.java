package blackjack.strategy;

import blackjack.domain.card.CardBundle;

@FunctionalInterface
public interface CanHitStrategy {

    boolean checkFinished(CardBundle cardBundle);
}
