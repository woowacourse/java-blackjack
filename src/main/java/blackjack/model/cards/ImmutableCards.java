package blackjack.model.cards;

import blackjack.model.Card;
import blackjack.model.Score;
import java.util.stream.Stream;

public class ImmutableCards implements Cards{

    private final Cards cards;

    public ImmutableCards(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Score bestScore() {
        return cards.bestScore();
    }

    @Override
    public Score maxScore() {
        return cards.maxScore();
    }

    @Override
    public void take(Card card) {
        throw new UnsupportedOperationException("현재 카드는 ImmutableCards 입니다.");
    }

    @Override
    public Stream<Card> stream() {
        return cards.stream();
    }
}
