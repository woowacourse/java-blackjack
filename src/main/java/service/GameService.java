package service;

import domain.Deck;
import domain.Participant;
import domain.Participants;
import domain.PrintedHandPool;

public class GameService {
    private static final int DEALER_MINIMUM_VALUE= 17;
    private static final String HIT_REQUEST = "y";
    private final Deck deck;
    private final Participants participants;
    private final PrintedHandPool printedHandPool;

    public GameService(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
        this.printedHandPool = new PrintedHandPool();
    }

    public void dealCardsToParticipants() {
        participants.deal(deck);
    }

    public boolean isHit(String drawingInput) {
        return drawingInput.equals(HIT_REQUEST);
    }

    public void hit(Participant participant) {
        participant.receiveCard(deck.draw());
    }

    public boolean isDealerHandValueUnderStandard() {
        return participants.findDealer().getHandValue() < DEALER_MINIMUM_VALUE;
    }

    public void dealerHit() {
        hit(participants.findDealer());
    }
}
