package domain.strategy;

import domain.card.Card;
import domain.CardsGenerator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class SettedCardsGenerator implements CardsGenerator {
    private final List<Card> cards;

    public SettedCardsGenerator(final List<Card> cards) {
        this.cards = cards.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> generate() {
        return cards;
    }
}
