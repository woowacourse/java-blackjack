package blackjack.domain.card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InitialCardsDrawStrategy implements DrawCardsStrategy {
    private static final int TOP_OF_DECK = 0;
    private static final int NUMBER_OF_INIT_CARD = 2;

    @Override
    public List<Card> drawCards(List<Card> cards) {
        return Stream.generate(() -> cards.remove(TOP_OF_DECK))
                .limit(NUMBER_OF_INIT_CARD)
                .collect(Collectors.toList());
    }
}
