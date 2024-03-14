package blackjack.domain.stategy;

import blackjack.strategy.shuffle.ShuffleStrategy;
import java.util.List;

public class NoShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(List<?> list) {
    }
}
