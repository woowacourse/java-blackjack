package blackjack.domain.card.strategy;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InitialCardsDrawStrategy implements DrawCardsStrategy {
    private static final int NUMBER_OF_INIT_CARD = 2;

    @Override
    public List<Card> drawCards(Stack<Card> cards) {
        return Stream.generate(() -> cards.pop())
                .limit(NUMBER_OF_INIT_CARD)
                .collect(Collectors.toList());
    }
}
