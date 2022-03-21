package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;
import java.util.List;

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
        return Score.calculateSumFrom(hand);
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    // TODO: 구현체의 canReceive 메소드와 상당히 겹침. 해결 필요.
    public boolean isBusted() {
        return Score.calculateSumFrom(hand).isBusted();
    }

    public boolean isBlackjack() {
        List<Card> initialCards = hand.getInitialCards();
        Score initialScore = Score.calculateSumFrom(initialCards);

        return initialScore.equals(Score.valueOf(Score.MAXIMUM_SCORE));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Participant that = (Participant) o;

        if (!name.equals(that.name)) {
            return false;
        }
        return hand.equals(that.hand);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + hand.hashCode();
        return result;
    }
}
