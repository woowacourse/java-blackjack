package strategy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class RandomCardGenerator implements CardGenerator {

    @Override
    public Queue<Card> generate() {
        return new LinkedList<>(allCardsShuffled());
    }

    private List<Card> allCardsShuffled() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            cards.addAll(allCardsWithSameSymbol(symbol));
        }
        Collections.shuffle(cards);
        return cards;
    }

    private List<Card> allCardsWithSameSymbol(Symbol symbol) {
        return Arrays.stream(Rank.values())
            .map(rank -> Card.of(rank, symbol))
            .collect(Collectors.toList());
    }
}
