package domain.strategy;

import domain.card.Card;
import domain.DecksGenerator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SettedDecksGenerator implements DecksGenerator {
    private final Stack<Card> cards;

    public SettedDecksGenerator(final List<Card> cards) {
        this.cards = cards.stream()
                .collect(Collectors.toCollection(Stack::new));
    }

    @Override
    public Stack<Card> generate() {
        return cards;
    }
}
