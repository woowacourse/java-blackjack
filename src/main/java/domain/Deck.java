package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = Arrays.stream(CardType.values())
                .flatMap(type -> Arrays.stream(CardNumber.values()).map(number -> Card.getCard(type, number)))
                .collect(Collectors.toList());
    }

    Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
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
