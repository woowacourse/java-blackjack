package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> cards;
    public Deck() {
        cards = initialize();
    }

    private List<Card> initialize() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(number, shape)))
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(cards);
        return cards;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public List<Card> handOutCards() {
        List<Card> handOutCards = new ArrayList<>();
        for (int index = 0; index < 2; index++) {
            handOutCards.add(peekCard());
        }
        return handOutCards;
    }

    public Card peekCard() {
        Card lastCard = cards.getLast();
        cards.remove(lastCard);
        return lastCard;
    }
}
