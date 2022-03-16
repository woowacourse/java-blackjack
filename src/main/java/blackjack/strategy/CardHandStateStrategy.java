package blackjack.strategy;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardHandState;

@FunctionalInterface
public interface CardHandStateStrategy {

    CardHandState getStateFrom(CardBundle cardBundle);
}
