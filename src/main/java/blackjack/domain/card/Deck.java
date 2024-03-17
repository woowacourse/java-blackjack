package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = initAllCards();
    }

    private List<Card> initAllCards() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Number.values()).map(number -> new Card(shape, number)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("모든 카드를 사용하였습니다.");
        }

        return cards.remove(0);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
