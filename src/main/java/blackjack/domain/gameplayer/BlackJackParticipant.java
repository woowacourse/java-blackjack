package blackjack.domain.gameplayer;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class BlackJackParticipant implements User {

    protected final Cards cards;
    protected final Score bustUpperBound = Score.of(21);

    public BlackJackParticipant(Cards cards) {
        this.cards = cards;
    }

    public boolean isBust() {
        return calculateScore().isGreaterThan(bustUpperBound);
    }

    public boolean isBlackJack() {
        return cards.getSize() == 2 && calculateScore().isEqualTo(bustUpperBound);
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public Score calculateScore() {
        return Score.from(cards);
    }
}
