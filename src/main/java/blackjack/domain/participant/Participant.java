package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;

public abstract class Participant {

    private final String name;
    private final Hand hand;

    protected Participant(final String name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public abstract void receiveCard(Card card);

    public abstract boolean canReceive();

    public Score getCurrentScore() {
        return hand.getScore();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isBusted() {
        return hand.getScore().isGreaterThan(Score.BLACKJACK);
    }

    // TODO: 조건식 단순화
    public ResultType compareWith(Participant other) {
        int playerScore = getCurrentScore().getValue();
        int otherScore = other.getCurrentScore().getValue();

        if ((playerScore > otherScore && !isBusted()) || (!isBusted() && other.isBusted())) {
            return ResultType.WIN;
        }
        if ((playerScore < otherScore && !other.isBusted()) || (isBusted() && !other.isBusted())) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }
}
