package blackjack.strategy;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;

public class RandomCardPicker implements CardPicker{

    @Override
    public Card pick(List<Card> cards) {
        if (cards.isEmpty()) {
            throw new IndexOutOfBoundsException("더 이상 카드가 없습니다.");
        }
        Collections.shuffle(cards);
        return cards.get(0);
    }
}
