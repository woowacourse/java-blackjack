package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BURST_THRESHOLD_NUMBER = 21;

    ParticipantCards participantCards;

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

    public boolean isBurst() {
        return getScore() > BURST_THRESHOLD_NUMBER;
    }

    public boolean isMoreOrEqualThanThreshold() {
        return getScore() >= BURST_THRESHOLD_NUMBER;
    }

    public abstract String getName();

}
