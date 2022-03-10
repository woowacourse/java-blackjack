package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;

public class Player extends Participant {

    private Player(final String name, final CardBundle cardBundle) {
        super(name, cardBundle);
    }

    public static Player of(final String name, final CardBundle cardBundle) {
        return new Player(name, cardBundle);
    }

    public void receiveCard(Card card) {
        cardBundle.add(card);
    }

    public boolean canReceive() {
        Score score = cardBundle.getScore();
        return score.toInt() <= Score.BLACKJACK;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cardBundle=" + cardBundle +
                '}';
    }
}
