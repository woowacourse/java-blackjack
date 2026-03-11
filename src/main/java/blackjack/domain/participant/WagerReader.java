package blackjack.domain.participant;

import blackjack.domain.money.Money;

@FunctionalInterface
public interface WagerReader {

    Money wagerOf(Player player);
}
