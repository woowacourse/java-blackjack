package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class CardDeck {
    public static final Queue<Card> cardDeck = createCardDeck();

    private CardDeck() {
    }

    private static Queue<Card> createCardDeck() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }

    private static List<Card> createCards() {
        return Arrays.stream(Symbol.values())
                .flatMap(symbol -> createSymbolCards(symbol).stream())
                .collect(Collectors.toList());
    }

    private static List<Card> createSymbolCards(Symbol symbol) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(cardNumber, symbol))
                .collect(Collectors.toList());
    }

    public static Card drawCard() {
        return cardDeck.poll();
    }

    public static List<Card> drawTwoCards() {
        return List.of(Objects.requireNonNull(cardDeck.poll()), Objects.requireNonNull(cardDeck.poll()));
    }

}
