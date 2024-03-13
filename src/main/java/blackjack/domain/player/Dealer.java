package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.ResultStatus;

public class Dealer extends Participant {
    public static final Integer RECEIVE_SIZE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer createDefaultDealer(final Cards cards) {
        return new Dealer(new Name(DEFAULT_DEALER_NAME), cards);
    }


    public ResultStatus checkPlayer(final GamePlayer gamePlayer) {
        final var playerScore = gamePlayer.calculateScore();
        final var dealerScore = calculateScore();
        if (gamePlayer.isBust()) {
            return ResultStatus.LOSE;
        }
        if (isBust()) {
            return ResultStatus.WIN;
        }
        return playerScore.compare(dealerScore);
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    @Override
    public boolean isReceivable() {
        return cards.sum() <= RECEIVE_SIZE;
    }
}
