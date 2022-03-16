package blackjack.strategy;

import blackjack.domain.card.CardBundle;

@FunctionalInterface
public interface CardBundleSupplier {

    CardBundle getInitialCardBundle();
}
