package playerState;

import card.Card;
import card.Hand;
import gameState.Draw;
import gameState.GameStatus;
import gameState.Lose;
import gameState.Win;

public class Stand extends PlayerStatus {

    private Stand(GameStatus gameStatus, Hand hand) {
        super(gameStatus, hand);
    }

    public static Stand of(GameStatus gameStatus, Hand hand) {
        if (hand.getPoint() > Hittable.BLACK_JACK_POINT) {
            throw new IllegalArgumentException("Stand 할 수 있는 카드 상태가 아닙니다.");
        }
        return new Stand(gameStatus, hand);
    }

    @Override
    public PlayerStatus addCard(Card card) {
        throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
    }

    public Stand getFinalState(PlayerStatus dealerStatus) {
        if (dealerStatus instanceof Bust) {
            return new Stand(new Win(gameStatus.getBettingAmount()), hand);
        }
        if (dealerStatus instanceof BlackJack) {
            return new Stand(new Lose(gameStatus.getBettingAmount()), hand);
        }
        if (dealerStatus.getPoint() > getPoint()) {
            return new Stand(new Lose(gameStatus.getBettingAmount()), hand);
        }
        if (dealerStatus.getPoint() == getPoint()) {
            return new Stand(new Draw(gameStatus.getBettingAmount()), hand);
        }
        return new Stand(new Win(gameStatus.getBettingAmount()), hand);
    }
}
