package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;

public class Player {
    private static final int MAXIMUM_SCORE = 21;

    private final String name;
    private final CardBundle cardBundle;

    private Player(final String name, final CardBundle cardBundle) {
        this.name = name;
        this.cardBundle = cardBundle;
    }

    public static Player of(final String name, final CardBundle cardBundle) {
        return new Player(name, cardBundle);
    }

    public void receiveCard(Card card) {
        cardBundle.add(card);
    }

    public boolean canReceive() {
        Score score = cardBundle.getScore();
        return score.toInt() <= MAXIMUM_SCORE;
    }

    public Score getCurrentScore() {
        return cardBundle.getScore();
    }

    public String getName() {
        return name;
    }

    public CardBundle getCardBundle() {
        return cardBundle;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cardBundle=" + cardBundle +
                '}';
    }
}
