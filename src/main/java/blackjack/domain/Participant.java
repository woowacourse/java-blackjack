package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private final ParticipantName participantName;
    private final List<Card> receivedCards = new ArrayList<>();

    public Participant(ParticipantName participantName) {
        this.participantName = participantName;
    }

    public void hit(Card card) {
        receivedCards.add(card);
    }

    public List<Card> getReceivedCards() {
        return receivedCards;
    }

    public boolean participantHasAceCard() {
        return receivedCards.stream()
            .anyMatch(card -> card.getCardNumber().equals(CardNumber.ACE));
    }

    public int calculateCardNumberAceCardValueOne() {
        return receivedCards.stream()
            .mapToInt(card -> card.getCardNumber().value)
            .sum();
    }

    abstract int calculateCardNumber();

}
