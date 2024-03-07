package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final ArrayDeque<Card> deck;

    public Deck(ArrayDeque<Card> cards) {
        this.deck = cards;
    }

    public static Deck createPack() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        return new Deck(new ArrayDeque<>(cards));
    }

    //TODO : 셔플 하기 위해 이게 최선인가?
    private static List<Card> createCards() {
        return Arrays.stream(CardSymbol.values())
                     .flatMap(Deck::addCard)
                     .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Stream<Card> addCard(final CardSymbol cardSymbol) {
        return EnumSet.allOf(CardValue.class)
                      .stream()
                      .map(cardNumber -> new Card(cardNumber, cardSymbol));
    }

    public Card draw() {
        return deck.pollLast();
    }

}
