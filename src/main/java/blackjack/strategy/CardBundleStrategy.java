package blackjack.strategy;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardStack;

@FunctionalInterface
public interface CardBundleStrategy {

    CardBundle initCardBundle(CardStack cardStack);
}
