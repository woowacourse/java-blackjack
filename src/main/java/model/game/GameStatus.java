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
            return evaluateInitialStatus(cards);
        }

        return evaluateOngoingStatus(cards);
    }

    private static GameStatus evaluateInitialStatus(Cards cards) {
        if (cards.calculateScore() == BLACKJACK_SCORE) {
            return BLACKJACK;
        }

        return CAN_HIT;
    }

    private static GameStatus evaluateOngoingStatus(Cards cards) {
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
