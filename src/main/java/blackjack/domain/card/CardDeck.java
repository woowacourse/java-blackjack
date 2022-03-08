package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class CardDeck {
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
        return cards.pop();
    }

    // TODO: handle NoSuchElementException on popping on empty list
}
