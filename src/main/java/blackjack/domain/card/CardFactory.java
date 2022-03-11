package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class CardFactory {

    private final Stack<Card> deck;

    private CardFactory(Stack<Card> deck) {
        this.deck = deck;
    }

    public static CardFactory create() {
        final List<Card> list = createAllCards();
        Collections.shuffle(list);

        final Stack<Card> deck = new Stack<>();
        deck.addAll(list);

        return new CardFactory(deck);
    }

    public static CardFactory createNoShuffle() {
        final List<Card> list = createAllCards();

        final Stack<Card> deck = new Stack<>();
        deck.addAll(list);

        return new CardFactory(deck);
    }

    private static List<Card> createAllCards() {
        return Arrays.stream(CardSymbol.values())
                .flatMap(symbol -> createCardsBySymbol(symbol).stream())
                .collect(Collectors.toList());
    }

    private static List<Card> createCardsBySymbol(CardSymbol symbol) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(symbol, cardNumber))
                .collect(Collectors.toList());
    }

    public Card drawCard() {
        return deck.pop();
    }


    public int getRemainAmount() {
        return deck.size();
    }
}
