package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import java.util.List;

public class Dealer implements Decidable {

    private static final int DEALER_MIN_RECEIVE_CARD = 16;

    private final Participant participant;

    public Dealer(Participant participant) {
        this.participant = participant ;
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

    @Override
    public List<Card> showInitCards() {
        return List.of(participant.getCards().get(0));
    }

    @Override
    public boolean canDraw() {
        return participant.calculateTotalScore() <= DEALER_MIN_RECEIVE_CARD;
    }

    public List<Card> getCards() {
        return participant.getCards();
    }
}
