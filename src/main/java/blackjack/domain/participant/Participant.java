package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;
import blackjack.domain.result.Score;
import java.util.List;

public abstract class Participant {

    protected final Cards cards;

    protected Participant() {
        this(new Cards());
    }

    protected Participant(final Cards cards) {
        this.cards = cards;
    }

    public void drawCard(final Card card) {
        if (!isDrawable()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
        }
        cards.addCard(card);
    }

    public Score getScore() {
        return new Score(cards.calculateTotalScore());
    }

    public Result getWinningStatus(final Score score) {
        return getScore().compare(score);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract boolean isDrawable();

    public abstract String getName();
}
