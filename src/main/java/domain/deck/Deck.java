package domain.deck;

import domain.card.Card;
import strategy.ShuffleStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final int DECKS_COUNT = 6;
    private static final String NO_CARDS = "카드가 모두 소진되었습니다.";
    private final Stack<Card> cards;

    public Deck(ShuffleStrategy shuffleStrategy) {
        List<Card> cards = Stream.generate(Card::createCardPack)
                .limit(DECKS_COUNT)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        this.cards = shuffleStrategy.shuffle(cards);
    }

    public Card draw() {
        if (cards.empty()) {
            throw new IllegalArgumentException(NO_CARDS);
        }
        return cards.pop();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
