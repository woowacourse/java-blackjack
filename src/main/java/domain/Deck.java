package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Deck {
    private final static List<Card> deck = new ArrayList<>();
    private List<Symbol> symbols;
    private List<CardNumber> cardNumbers;

    private Deck(List<Symbol> symbols, List<CardNumber> cardNumbers) {
        this.symbols = symbols;
        this.cardNumbers = cardNumbers;
    }

    public static Deck of(List<Symbol> symbols, List<CardNumber> cardNumbers) {
        Deck deck = new Deck(symbols, cardNumbers);
        generateCards(symbols, cardNumbers);
        return deck;
    }

    private static void generateCards(List<Symbol> symbols, List<CardNumber> cardNumbers) {
        if(Objects.isNull(symbols) || Objects.isNull(cardNumbers)) {
            symbols = Arrays.stream(Symbol.values()).collect(Collectors.toList());
            cardNumbers = Arrays.stream(CardNumber.values()).collect(Collectors.toList());
        }
        for (Symbol symbol : symbols) {
            addCardsWithSymbolOf(symbol, cardNumbers);
        }
        Collections.shuffle(deck);
    }

    private static void addCardsWithSymbolOf(Symbol symbol, List<CardNumber> cardNumbers) {
        for (CardNumber cardNumber : cardNumbers) {
            deck.add(new Card(symbol, cardNumber));
        }
    }

    public Card draw() {
        if (deck.size() == 0) {
            generateCards(this.symbols, this.cardNumbers);
        }
        return deck.remove(0);
    }
}
