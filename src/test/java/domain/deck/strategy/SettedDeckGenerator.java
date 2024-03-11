package domain.deck.strategy;

import domain.deck.DeckGenerator;
import domain.card.Card;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class SettedDeckGenerator implements DeckGenerator {
    private final Stack<Card> cards;

    public SettedDeckGenerator(final List<Card> cards) {
        this.cards = cards.stream()
                .collect(Collectors.toCollection(Stack::new));
    }

    @Override
    public Stack<Card> generate() {
        return cards;
    }
}
