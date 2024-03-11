package blackjack.domain.stategy;

import blackjack.strategy.ShuffleStrategy;
import java.util.List;

public class NoShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(List<?> list) {
    }
}
