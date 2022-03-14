package blackjack.domain.card;

import java.util.*;

public class Deck {

    private static final String NO_MORE_CARDS = "더이상 뽑을 수 있는 카드가 없습니다.";

    private final Deque<Card> cards = new ArrayDeque<>();

    public Deck() {
        List<Card> allCards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            addCardWithDenomination(allCards, symbol);
        }
        Collections.shuffle(allCards);

        cards.addAll(allCards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(NO_MORE_CARDS);
        }
        return cards.pop();
    }

    private void addCardWithDenomination(List<Card> allCards, Symbol symbol) {
        for (Denomination denomination : Denomination.values()) {
            allCards.add(new Card(symbol, denomination));
        }
    }
}
