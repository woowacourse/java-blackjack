package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;
import java.util.List;

public class Dealer implements Decidable {

    private static final int DEALER_MIN_RECEIVE_CARD = 16;
    private static final String DEALER_NAME = "딜러";

    private final Participant participant;

    public Dealer() {
        this.participant = new Participant(Hand.generateEmptyCards(), DEALER_NAME) ;
    }

    public void receiveCard(Card card) {
        participant.receiveCard(card);
    }

    public Score calculateTotalScore() {
        return participant.calculateTotalScore();
    }

    public boolean isBust() {
        return participant.isBust();
    }

    public boolean isBlackjack() {
        return participant.isBlackjack();
    }

    @Override
    public List<Card> showInitCards() {
        return List.of(participant.getHand().get(0));
    }

    @Override
    public boolean canDraw() {
        Score score = participant.calculateTotalScore();
        return score.isLessOrEquals(new Score(DEALER_MIN_RECEIVE_CARD));
    }

    public List<Card> getCards() {
        return participant.getHand();
    }

    public String getName() {
        return participant.getName();
    }
}
