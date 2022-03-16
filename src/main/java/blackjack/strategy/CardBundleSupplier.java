package blackjack.strategy;

import blackjack.domain.card.CardBundle;

public interface CardBundleSupplier {

    CardBundle getInitialCardBundle();
}
