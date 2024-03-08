package domain.strategy;

import domain.Deck;
import domain.DecksGenerator;
import domain.card.Card;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShuffledDecksGenerator implements DecksGenerator {
    private static final int DECKS_COUNT = 6;

    @Override
    public Stack<Card> generate() {
        Stack<Card> decks = Stream.generate(Deck::new)
                .limit(DECKS_COUNT)
                .flatMap(deck -> deck.getCards().stream())
                .collect(Collectors.toCollection(Stack::new));
        Collections.shuffle(decks);
        return decks;
    }
}
