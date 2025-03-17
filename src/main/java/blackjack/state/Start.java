package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public class Start implements State {
    private final CardHand cardHand;

    public Start(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("게임 시작 전에는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State drawInitialCards(Card card1, Card card2) {
        CardHand cardHand = new CardHand();
        cardHand.add(card1);
        cardHand.add(card2);
        if (cardHand.isBlackjack()) {
            return new Blackjack(cardHand);
        }
        return new Hit(cardHand);
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("게임 시작 전에는 stand할 수 없습니다.");
    }

    @Override
    public GameResult determineResult(State otherState) {
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
