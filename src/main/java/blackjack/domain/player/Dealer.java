package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.common.Name;
import blackjack.domain.result.ResultStatus;

public class Dealer extends Player {
    public static final Integer RECEIVE_SIZE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer createDefaultDealer(final Cards cards) {
        return new Dealer(new Name(DEFAULT_DEALER_NAME), cards);
    }


    public ResultStatus checkPlayer(final GamePlayer gamePlayer) {
        final int playerScore = gamePlayer.calculateScore();
        final int dealerScore = calculateScore();

        if (gamePlayer.isBust()) {
            return ResultStatus.LOSE;
        }
        if (isBust()) {
            return ResultStatus.WIN;
        }
        if (playerScore > dealerScore) {
            return ResultStatus.WIN;
        }
        if (playerScore == dealerScore) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    @Override
    public boolean isReceivable() {
        return cards.sum() <= RECEIVE_SIZE;
    }
}
