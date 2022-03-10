package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class CardDeck implements CardStack {

    private final LinkedList<Card> cards = new LinkedList<>();

    public CardDeck() {
        initCards();
        Collections.shuffle(cards);
    }

    private void initCards() {
        Arrays.stream(CardRank.values())
                .forEach(this::createAllSymbols);
    }

    private void createAllSymbols(CardRank rank) {
        Arrays.stream(CardSymbol.values())
                .forEach((symbol -> cards.add(Card.of(rank, symbol))));
    }

    public Card pop() {
        // TODO: handle NoSuchElementException on popping on empty list
        return cards.pop();
    }

    @Override
    public String toString() {
        return "CardDeck{" + "cards=" + cards + '}';
    }
}
