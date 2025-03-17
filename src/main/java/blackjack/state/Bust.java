package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public class Bust implements State{
    private final CardHand cardHand;

    public Bust(CardHand cardHand) {
        this.cardHand = cardHand;
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
    public GameResult determineResult(State otherState) {
        return GameResult.LOSE;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isHit() {
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
