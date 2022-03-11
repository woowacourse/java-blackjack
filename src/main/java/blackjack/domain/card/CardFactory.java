package blackjack.domain.card;

import java.util.ArrayList;
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
        final List<Card> list = getCards();
        Collections.shuffle(list);

        final Stack<Card> deck = new Stack<>();
        deck.addAll(list);

        return new CardFactory(deck);
    }

    public static CardFactory createNoShuffle() {
        final List<Card> list = getCards();

        final Stack<Card> deck = new Stack<>();
        deck.addAll(list);

        return new CardFactory(deck);
    }

    private static List<Card> getCards() {
        final List<Card> list = new ArrayList<>();
        for (CardSymbol symbol : CardSymbol.values()) {
            list.addAll(createSymbolCards(symbol));
        }

        return list;
    }

    private static List<Card> createSymbolCards(CardSymbol symbol) {
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
