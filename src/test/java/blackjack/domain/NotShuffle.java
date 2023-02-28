package blackjack.domain;

import java.util.List;

public class NotShuffle implements Shuffle {

    @Override
    public void shuffle(final List<?> before) {
        return;
    }
}
