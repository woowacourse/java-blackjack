package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BUST_THRESHOLD_NUMBER = 21;

    private ParticipantCards participantCards;

    public void receiveInitCards(List<Card> cards) {
        participantCards = new ParticipantCards(new ArrayList<>(cards));
    }

    public void receiveCard(Card card) {
        participantCards.addCard(card);
    }

    public int getScore() {
        return participantCards.calculateScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(participantCards.getCards());
    }

    public boolean isBust() {
        return getScore() > BUST_THRESHOLD_NUMBER;
    }

    public boolean isMoreThanThreshold() {
        return getScore() >= BUST_THRESHOLD_NUMBER;
    }

    public abstract String getName();

}
