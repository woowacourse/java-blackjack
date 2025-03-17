package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public abstract class Finished implements State {
    final CardHand cardHand;

    Finished(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    @Override
    public abstract GameResult determineResult(State otherState);

    @Override
    public abstract boolean isBlackjack();

    @Override
    public abstract boolean isBust();

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("게임이 종료된 카드는 카드를 발급받을 수 없습니다.");
    }

    @Override
    public State drawInitialCards(Card card1, Card card2) {
        throw new UnsupportedOperationException("게임 시작시에만 카드를 초기화할 수 있습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("게임이 종료된 카드는 stand할 수 없습니다.");
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
