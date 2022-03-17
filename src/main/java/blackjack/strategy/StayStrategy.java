package blackjack.strategy;

import blackjack.domain.card.CardBundle;

@FunctionalInterface
public interface StayStrategy {

    boolean checkFinished(CardBundle cardBundle);
}
