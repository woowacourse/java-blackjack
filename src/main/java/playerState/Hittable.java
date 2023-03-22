package playerState;

import card.Card;
import card.Hand;
import gameState.GameStatus;

public class Hittable extends PlayerStatus {

    public static final int BLACK_JACK_POINT = 21;
    public static final int BLACK_CARD_COUNT = 2;

    private Hittable(GameStatus gameStatus, Hand hand) {
        super(gameStatus, hand);
    }

    public static Hittable of(GameStatus gameStatus, Hand hand) {
        if (hand.getPoint() >= BLACK_JACK_POINT) {
            throw new IllegalArgumentException("카드를 더 뽑을 수 있는 상태가 아닙니다.");
        }
        return new Hittable(gameStatus, hand);
    }

    public PlayerStatus addCard(Card card) {
        if (card == null) {
            return Stand.of(gameStatus, hand);
        }
        Hand newHand = hand.addCard(card);
        int newPoint = newHand.getPoint();
        if (newPoint > BLACK_JACK_POINT) {
            return Bust.of(gameStatus, newHand);
        }
        if (newPoint == BLACK_JACK_POINT) {
            if (newHand.getSize() == BLACK_CARD_COUNT) {
                return BlackJack.of(gameStatus, newHand);
            } else {
                return Stand.of(gameStatus, newHand);
            }
        }
        return new Hittable(gameStatus, newHand);
    }

    @Override
    public Stand getFinalState(PlayerStatus dealerStatus) {
        throw new IllegalStateException("아직 게임 중 입니다.");
    }
}
