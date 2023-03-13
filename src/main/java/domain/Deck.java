package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class Deck {
    private final Queue<Card> deck;

    private Deck(Queue<Card> cards) {
        this.deck = cards;
    }

    public static Deck of(List<Symbol> symbols, List<CardNumber> cardNumbers) {
        List<Card> cards = new LinkedList<>();
        generateCards(symbols, cardNumbers, cards);
        Deck deck = new Deck(new LinkedList<>(cards));
        return deck;
    }

    private static void generateCards(List<Symbol> symbols, List<CardNumber> cardNumbers, List<Card> cards) {
        if (Objects.isNull(symbols) || Objects.isNull(cardNumbers)) {
            symbols = Arrays.stream(Symbol.values()).collect(Collectors.toList());
            cardNumbers = Arrays.stream(CardNumber.values()).collect(Collectors.toList());
        }
        for (Symbol symbol : symbols) {
            addCardsWithSymbolOf(symbol, cardNumbers, cards);
        }
        Collections.shuffle(cards);
    }

    private static void addCardsWithSymbolOf(Symbol symbol, List<CardNumber> cardNumbers, List<Card> cards) {
        for (CardNumber cardNumber : cardNumbers) {
            cards.add(new Card(symbol, cardNumber));
        }
    }

    public Card draw() {
        if (deck.isEmpty()) {
            List<Card> cards = new ArrayList<>();
            generateCards(null, null, cards);
            this.deck.addAll(cards);
        }
        return deck.remove();
    }
}
