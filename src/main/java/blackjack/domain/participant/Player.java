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
        hand.add(card);
    }

    public boolean canReceive() {
        Score score = hand.getScore();
        return score.toInt() <= Score.BLACKJACK;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                '}';
    }
}
