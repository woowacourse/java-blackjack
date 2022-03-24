package blackjack.model.card;

import blackjack.model.game.DrawStrategy;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CardDeck implements DrawStrategy {
    private final Deque<Card> cards;

    public CardDeck() {
        this.cards = createCards();
    }

    private LinkedList<Card> createCards() {
        final LinkedList<Card> cards = new LinkedList<>();
        for (CardSymbol cardSymbol : CardSymbol.values()) {
            pushCardFromSymbol(cards, cardSymbol);
        }
        Collections.shuffle(cards);
        return cards;
    }

    private void pushCardFromSymbol(final List<Card> cards, CardSymbol cardSymbol) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardNumber, cardSymbol));
        }
    }

    @Override
    public Card draw() {
        if (isNotEmpty()) {
            return cards.pop();
        }
        throw new IllegalArgumentException("[ERROR] 카드 덱에 남은 카드가 없어 카드를 받을수 없습니다.");
    }

    private boolean isNotEmpty() {
        return !cards.isEmpty();
    }
}
