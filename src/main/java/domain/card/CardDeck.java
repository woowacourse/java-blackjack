package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck() {
        cards = initializeCardPack();
        shuffle();
    }

    private List<Card> initializeCardPack() {
        return new ArrayList<>(Arrays.stream(Rank.values())
                        .flatMap(rank -> Arrays.stream(Shape.values())
                                .map(shape -> new Card(rank, shape)))
                                .toList());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card poll() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 모두 소진되었습니다.");
        }
        return cards.removeFirst();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
