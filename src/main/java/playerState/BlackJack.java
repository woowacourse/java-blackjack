package playerState;

import card.Card;
import card.Hand;
import gameState.Draw;
import gameState.GameStatus;
import gameState.Win;

public class BlackJack extends PlayerStatus {

    private BlackJack(GameStatus gameStatus, Hand hand) {
        super(gameStatus, hand);
    }

    public static BlackJack of(GameStatus gameStatus, Hand hand) {
        if (hand.getSize() != 2 || hand.getPoint() != Hittable.BLACK_JACK_POINT) {
            throw new IllegalArgumentException("블랙잭이 아닙니다.");
        }
        return new BlackJack(gameStatus, hand);
    }

    @Override
    public PlayerStatus addCard(Card card) {
        throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
    }


    @Override
    public BlackJack getFinalState(PlayerStatus dealerStatus) {
        if (dealerStatus instanceof BlackJack) {
            return new BlackJack(new Draw(gameStatus.getBettingAmount()), hand);
        }
        return new BlackJack(new Win(gameStatus.getBettingAmount()), hand);
    }
}
