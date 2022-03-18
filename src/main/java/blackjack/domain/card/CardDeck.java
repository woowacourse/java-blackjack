package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CardDeck implements CardStack {

    private static final String EMPTY_CARD_DECK_EXCEPTION_MESSAGE = "카드가 모두 소진되었습니다!";

    private final LinkedList<Card> cards = new LinkedList<>();

    public CardDeck() {
        initCards();
        Collections.shuffle(cards);
    }

    private void initCards() {
        for (CardRank rank : CardRank.values()) {
            initAndAddAllSymbolsOf(rank);
        }
    }

    private void initAndAddAllSymbolsOf(final CardRank rank) {
        for (CardSymbol symbol : CardSymbol.values()) {
            Card card = Card.of(rank, symbol);
            cards.add(card);
        }
    }

    @Override
    public Card pop() {
        try {
            return cards.pop();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(EMPTY_CARD_DECK_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "CardDeck{" + "cards=" + cards + '}';
    }
}
