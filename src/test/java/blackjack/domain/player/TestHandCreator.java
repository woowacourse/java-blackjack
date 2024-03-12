package blackjack.domain.player;

import blackjack.domain.card.TestCardCreator;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestHandCreator {

    private TestHandCreator() {
    }

    public static Hand of(int... numbers) {
        return new Hand(Arrays.stream(numbers)
                .mapToObj(TestCardCreator::from)
                .collect(Collectors.toList()));
    }
}
