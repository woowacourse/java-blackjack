package domain.strategy;

import domain.DecksGenerator;
import domain.card.Card;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


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
