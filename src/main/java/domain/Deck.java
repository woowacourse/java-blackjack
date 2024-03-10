package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final List<Card> CARDS_CACHE = Arrays.stream(CardType.values())
            .flatMap(type -> Arrays.stream(CardNumber.values())
                    .map(number -> new Card(type, number)))
            .toList();
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>(CARDS_CACHE);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new RuntimeException("카드가 더이상 존재하지 않습니다.");
        }
        return cards.remove(cards.size() - 1);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
