package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import strategy.CardGenerator;

public class SequentialCardGenerator implements CardGenerator {

    @Override
    public Queue<Card> generate() {
        return new LinkedList<>(allCards());
    }

    private List<Card> allCards() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            cards.addAll(allCardsWithSameSymbol(symbol));
        }
        return cards;
    }

    private List<Card> allCardsWithSameSymbol(Symbol symbol) {
        return Arrays.stream(Rank.values())
            .map(rank -> new Card(rank, symbol))
            .collect(Collectors.toList());
    }
}
