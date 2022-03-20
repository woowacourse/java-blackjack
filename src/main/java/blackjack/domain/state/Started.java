package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayRecord;

public abstract class Started implements State {

    final Cards cards;

    Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public abstract State draw(Card card);

    @Override
    public final Cards getCards() {
        return cards;
    }

    @Override
    public abstract State stay();

    @Override
    public boolean isDrawable() {
        return true;
    }

    @Override
    public long revenue(PlayRecord playRecord, Bet bet) {
        throw new IllegalStateException("Finished 상태가 아니면 수익을 반환할 수 없습니다.");
    }
}
