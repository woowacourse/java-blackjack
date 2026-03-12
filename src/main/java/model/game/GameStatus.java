package model.game;

import static model.game.Blackjack.BLACKJACK_SCORE;
import static model.game.Blackjack.DEALOUT_DRAW_COUNT;

import model.card.Cards;

public enum GameStatus {
    NEED_DEALOUT(true),
    CAN_HIT(true),
    BLACKJACK(false),
    STAY_21(false),
    BUST(false),
    ;

    private final boolean canReceive;

    GameStatus(boolean canReceive) {
        this.canReceive = canReceive;
    }

    public GameStatus getNextStatus(Cards cards) {
        if (cards.size() < DEALOUT_DRAW_COUNT) {
            return NEED_DEALOUT;
        }

        if (cards.size() == DEALOUT_DRAW_COUNT) {
            // 이 분기에서 버스트가 발생하지 않음은 보장된다.

            if (cards.calculateScore() == BLACKJACK_SCORE) {
                return BLACKJACK;
            }

            return CAN_HIT;
        }

        // 여기서부터는 무조건 카드 개수가 3개 이상이다.

        if (cards.calculateScore() == BLACKJACK_SCORE) {
            return STAY_21;
        }

        if (cards.calculateScore() > BLACKJACK_SCORE) {
            return BUST;
        }

        return CAN_HIT;
    }

    public boolean canReceive() {
        return this.canReceive;
    }
}
