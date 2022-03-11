package blackjack.model.cards;

import blackjack.model.card.Card;
import java.util.Iterator;
import java.util.stream.Stream;

final class ImmutableCards implements Cards{

    private final Cards cards;

    ImmutableCards(Cards cards) {
        this.cards = cards;
    }

    @Override
    public void take(Card card) {
        throw new UnsupportedOperationException("현재 카드는 ImmutableCards 입니다.");
    }

    @Override
    public Stream<Card> stream() {
        return cards.stream();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
