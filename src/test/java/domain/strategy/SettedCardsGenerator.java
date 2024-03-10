package domain.strategy;

import domain.card.Card;
import domain.CardsGenerator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class SettedCardsGenerator implements CardsGenerator {
    private final Stack<Card> cards;

    public SettedCardsGenerator(final List<Card> cards) {
        this.cards = cards.stream()
                .collect(Collectors.toCollection(Stack::new));
    }

    @Override
    public Stack<Card> generate() {
        return cards;
    }
}
