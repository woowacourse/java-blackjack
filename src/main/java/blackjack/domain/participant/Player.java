package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;

public class Player extends Participant {

    private static final String INVALID_NAME_LENGTH_EXCEPTION_MESSAGE = "이름은 1글자 이상이어야합니다.";

    private Player(final String name, final Hand hand) {
        super(name, hand);
    }

    public static Player of(final String name, final Hand hand) {
        validateNameNotEmpty(name);
        return new Player(name, hand);
    }

    private static void validateNameNotEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_EXCEPTION_MESSAGE);
        }
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
