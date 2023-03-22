package playerState.dealerState;

import card.Card;
import card.Hand;
import gameState.GameStatus;
import playerState.BlackJack;
import playerState.Bust;
import playerState.Hittable;
import playerState.PlayerStatus;
import playerState.Stand;

public class UnderSixteen extends PlayerStatus {

    public static final int DEALER_THRESHOLDS = 16;

    private UnderSixteen(GameStatus gameStatus, Hand hand) {
        super(gameStatus, hand);
    }

    public static UnderSixteen of(GameStatus gameStatus, Hand hand) {
        if (hand.getPoint() > 16) {
            throw new IllegalArgumentException("카드의 총합이 16이 넘습니다.");
        }
        return new UnderSixteen(gameStatus, hand);
    }

    @Override
    public PlayerStatus addCard(Card card) {
        Hand newHand = hand.addCard(card);
        int newPoint = newHand.getPoint();
        if (newPoint < DEALER_THRESHOLDS) {
            return new UnderSixteen(gameStatus, newHand);
        }
        if (newPoint > Hittable.BLACK_JACK_POINT) {
            return Bust.of(gameStatus, newHand);
        }
        if (newPoint == Hittable.BLACK_JACK_POINT) {
            if (newHand.getSize() == Hittable.BLACK_CARD_COUNT) {
                return BlackJack.of(gameStatus, newHand);
            } else {
                return Stand.of(gameStatus, newHand);
            }
        }
        return Stand.of(gameStatus, newHand);
    }

    @Override
    public PlayerStatus getFinalState(PlayerStatus playerStatus) {
        throw new IllegalStateException("딜러는 16점이 넘을 때 까지 카드를 받아야 합니다.");
    }
}
