package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Deck {
    private final Queue<Card> deck;

    private Deck(List<Symbol> symbols, List<CardNumber> cardNumbers) {
        this.deck = new LinkedList<>(generateCards(symbols, cardNumbers));
    }

    public static Deck of(List<Symbol> symbols, List<CardNumber> cardNumbers) {
        return new Deck(symbols, cardNumbers);
    }

    private static List<Card> generateCards(List<Symbol> symbols, List<CardNumber> cardNumbers) {
        List<Card> cards = new LinkedList<>();
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
        if (deck.isEmpty()) {
            this.deck.addAll(generateCards(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                    Arrays.stream(CardNumber.values()).collect(Collectors.toList())));
        }
        return deck.remove();
    }
}
