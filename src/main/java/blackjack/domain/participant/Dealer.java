package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    private Dealer(final String name, final Hand hand) {
        super(name, hand);
    }

    public static Dealer of(final Hand hand) {
        return new Dealer(DEALER_NAME, hand);
    }

    public void receiveCard(Card card) {
        getHand().add(card);
    }

    public boolean canReceive() {
        Score score = getHand().getScore();
        return score.isLessOrEqualThan(Score.DEALER_EXTRA_CARD_LIMIT);
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name='" + getName() + '\'' +
                ", hand=" + getHand() +
                '}';
    }
}
