package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;

public abstract class Participant {

    protected final String name;
    protected CardBundle cardBundle;

    protected Participant(final String name, final CardBundle cardBundle) {
        this.name = name;
        this.cardBundle = cardBundle;
    }

    public abstract void receiveCard(Card card);

    public abstract boolean canReceive();

    public Score getCurrentScore() {
        return cardBundle.getScore();
    }

    public String getName() {
        return name;
    }

    public CardBundle getCardBundle() {
        return cardBundle;
    }

    public ResultType compareWith(Participant other) {
        int playerScore = getCurrentScore().toInt();
        int otherScore = other.getCurrentScore().toInt();

        if (playerScore > otherScore) {
            return ResultType.WIN;
        }
        if (playerScore < otherScore) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }
}
