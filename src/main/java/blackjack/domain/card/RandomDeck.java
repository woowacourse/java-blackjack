package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomDeck implements Deck {
    private final List<Card> cards;

    public RandomDeck() {
        this.cards = Stream.generate(this::createDeck)
                .limit(8)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        Collections.shuffle(this.cards);
    }

    private List<Card> createDeck() {
        return Stream.of(Number.values())
                .flatMap(number -> Stream.of(Shape.values())
                        .map(shape -> new Card(number, shape)))
                .collect(Collectors.toList());
    }

    public List<Card> drawInitialCards() {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(drawCard());
        initialCards.add(drawCard());
        return initialCards;
    }

    public Card drawCard() {
        return cards.remove(0);
    }
}
