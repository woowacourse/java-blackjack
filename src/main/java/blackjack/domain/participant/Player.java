package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;

public class Player extends Participant {

    private Player(final String name, final Hand hand) {
        super(name, hand);
    }

    public static Player of(final String name, final Hand hand) {
        return new Player(name, hand);
    }

    public void receiveCard(Card card) {
        getHand().add(card);
    }

    public boolean canReceive() {
        Score score = getHand().getScore();
        return score.isLessOrEqualThan(Score.BLACKJACK);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + getName() + '\'' +
                ", hand=" + getHand() +
                '}';
    }
}
