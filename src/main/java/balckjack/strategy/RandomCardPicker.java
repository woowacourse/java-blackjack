package balckjack.strategy;

import balckjack.domain.Card;
import balckjack.domain.CardPool;
import java.util.Collections;
import java.util.List;

public class RandomCardPicker implements CardPicker{

    @Override
    public Card pick() {
        final List<Card> cards = CardPool.getCards();
        if (cards.isEmpty()) {
            throw new IndexOutOfBoundsException("더 이상 카드가 없습니다.");
        }
        Collections.shuffle(cards);
        return cards.get(0);
    }
}
