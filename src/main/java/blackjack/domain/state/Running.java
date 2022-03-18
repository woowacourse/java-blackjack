package blackjack.domain.state;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public class Running implements State {

    private final Cards cards;

    Running(Cards cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드패는 null일 수 없습니다.");
        this.cards = cards;
    }

    public static State start(List<Card> initialCards) {
        Cards cards = new Cards(initialCards);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Running(cards);
    }

    @Override
    public State hit(Card card) {
        Cards cards = this.cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Running(cards);
    }

    @Override
    public State stand() {
        return new Stand(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public Score calculateScore() {
        return cards.calculateScore();
    }
}
