package domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck() {
        cards = initializeCardPack();
        shuffle();
    }

    private List<Card> initializeCardPack() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Shape.values())
                        .map(shape -> new Card(rank, shape)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card poll() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("카드가 모두 소진되었습니다.");
        }
        return cards.removeFirst();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
