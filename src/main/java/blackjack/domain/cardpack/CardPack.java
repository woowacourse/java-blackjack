package blackjack.domain.cardpack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.ArrayList;
import java.util.List;

public class CardPack {

    private final List<Card> cards;

    public CardPack() {
        cards = new ArrayList<>();
        initCards();
    }

    private void initCards() {
        for (final CardShape currentShape : CardShape.values()) {
            for (final CardNumber currentNumber : CardNumber.values()) {
                cards.add(new Card(currentNumber, currentShape));
            }
        }
    }

    public Card get(final int i) {
        return cards.get(i);
    }

    public void shuffle(final ShuffleStrategy strategy) {
        strategy.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }
}
