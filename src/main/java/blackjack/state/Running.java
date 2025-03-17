package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public abstract class Running implements HandState {
    final CardHand cardHand;

    Running(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    @Override
    public HandState drawInitialCards(Card card1, Card card2) {
        throw new UnsupportedOperationException("게임 시작시에만 카드를 초기화할 수 있습니다.");
    }

    @Override
    public HandState stand() {
        return new Stand(cardHand);
    }

    @Override
    public GameResult determineResult(HandState otherHandState) {
        throw new UnsupportedOperationException("게임 종료 전에는 승패를 계산할 수 없습니다.");
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
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score getScore() {
        return cardHand.getScore();
    }

    @Override
    public List<Card> getCards() {
        return cardHand.getCards();
    }
}
