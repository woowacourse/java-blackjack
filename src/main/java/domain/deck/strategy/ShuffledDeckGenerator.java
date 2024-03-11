package domain.deck.strategy;

import domain.deck.DeckGenerator;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShuffledDeckGenerator implements DeckGenerator {
    private static final int DECKS_COUNT = 6;

    @Override
    public Stack<Card> generate() {
        Stack<Card> decks = Stream.generate(this::createCardPack)
                .limit(DECKS_COUNT)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(Stack::new));
        Collections.shuffle(decks);
        return decks;
    }

    private List<Card> createCardPack(){
        return Stream.of(Symbol.values())
                .flatMap(symbol -> Rank.getRanks().stream()
                        .map(rank -> new Card(symbol, rank)))
                .toList();
    }
}
