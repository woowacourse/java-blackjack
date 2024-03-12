package blackjack.domain.card;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestCardDeckCreator {

    private TestCardDeckCreator() {
    }

    public static CardDeck createFrom(int... cards) {
        return new CardDeck(Arrays.stream(cards)
                .mapToObj(TestCardCreator::from)
                .collect(Collectors.toList()));
    }
}
