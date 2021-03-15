package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public class Hit implements State {
    private final Cards cards;

    public Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State takeCard(Card card) {
        cards.takeCard(card);
        final Score score = calculateScore();

        if (score.isBurst()) {
            return new Burst(cards);
        }
        if (score.isBlackjack()) {
            return new Blackjack(cards);
        }

        return new Hit(cards);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBurst() {
        return false;
    }

    @Override
    public Score calculateScore() {
        return cards.sumCards();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public int size() {
        return cards.size();
    }

}
