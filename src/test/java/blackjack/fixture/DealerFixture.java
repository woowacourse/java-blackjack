package blackjack.fixture;

import blackjack.domain.card.strategy.ReverseCardShuffleStrategy;
import blackjack.domain.participant.Dealer;

public class DealerFixture {

    public static Dealer 딜러() {
        return new Dealer(new ReverseCardShuffleStrategy());
    }
}
