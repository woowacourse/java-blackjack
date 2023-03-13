package playerState;

import card.Card;
import card.Hand;
import gameState.Draw;
import gameState.GameStatus;
import gameState.Lose;

public class Bust extends PlayerStatus {

    private Bust(GameStatus gameStatus, Hand hand) {
        super(gameStatus, hand);
    }

    public static Bust of(GameStatus gameStatus, Hand hand) {
        if (hand.getPoint() <= Hittable.BLACK_JACK_POINT) {
            throw new IllegalArgumentException("Bust가 아닙니다.");
        }
        return new Bust(gameStatus, hand);
    }

    @Override
    public PlayerStatus addCard(Card card) {
        throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
    }

    public Bust getFinalState(PlayerStatus dealerStatus) {
        if (dealerStatus instanceof Bust) {
            return new Bust(new Draw(gameStatus.getBettingAmount()), hand);
        }
        return new Bust(new Lose(gameStatus.getBettingAmount()), hand);
    }
}
