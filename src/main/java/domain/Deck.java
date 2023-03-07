package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Deck {
    private Deque<Card> deck;
    private final List<Symbol> symbols;
    private final List<CardNumber> cardNumbers;

    private Deck(List<Symbol> symbols, List<CardNumber> cardNumbers, Deque<Card> cards) {
        this.symbols = symbols;
        this.cardNumbers = cardNumbers;
        this.deck = cards;
    }

    public static Deck of(List<Symbol> symbols, List<CardNumber> cardNumbers) {
        List<Card> cards = generateCards(symbols, cardNumbers);
        Deck deck = new Deck(symbols, cardNumbers, new LinkedList<>(cards));
        return deck;
    }

    private static List<Card> generateCards(List<Symbol> symbols, List<CardNumber> cardNumbers) {
        List<Card> cards = new LinkedList<>();
        if(Objects.isNull(symbols) || Objects.isNull(cardNumbers)) {
            symbols = Arrays.stream(Symbol.values()).collect(Collectors.toList());
            cardNumbers = Arrays.stream(CardNumber.values()).collect(Collectors.toList());
        }
        for (Symbol symbol : symbols) {
            addCardsWithSymbolOf(symbol, cardNumbers, cards);
        }
        Collections.shuffle(cards);
        return cards;
    }

    private static void addCardsWithSymbolOf(Symbol symbol, List<CardNumber> cardNumbers, List<Card> cards) {
        for (CardNumber cardNumber : cardNumbers) {
            cards.add(new Card(symbol, cardNumber));
        }
    }

    public Card draw() {
        if (deck.size() == 0) {
            this.deck = new LinkedList<>(generateCards(this.symbols, this.cardNumbers));
        }
        return this.deck.removeFirst();
    }
}
