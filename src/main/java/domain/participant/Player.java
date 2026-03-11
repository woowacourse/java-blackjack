package domain.participant;

import static domain.constant.GameRule.BLACKJACK_CRITERION;
import static domain.constant.GameRule.INIT_CARD_COUNT;

import domain.enums.Result;

public class Player extends Participant {

    private final Name name;

    public Player(String name) {
        super();
        this.name = new Name(name);
    }

    public Result calculateResult(int dealerScore, boolean dealerBust) {
        int playerScore = cardBoard.calculateScore();
        if (cardBoard.isBust() || (playerScore < dealerScore && !dealerBust)) {
            return Result.LOSE;
        }
        if (playerScore == dealerScore) {
            return Result.DRAW;
        }
        if (cardBoard.getCards().size() == INIT_CARD_COUNT && playerScore == BLACKJACK_CRITERION) {
            return Result.WIN_BLACKJACK;
        }
        return Result.WIN;
    }

    public String getName() {
        return name.name();
    }
}
