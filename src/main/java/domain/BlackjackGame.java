package domain;

import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_CARD_AMOUNT = 2;

    private final Deck deck;
    private final Participants participants;

    private BlackjackGame(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static BlackjackGame from(Participants participants, Deck deck) {
        Dealer dealer = new Dealer();
        participants.addDealer(dealer);

        return new BlackjackGame(deck, participants);
    }

    public void handOutInitialCards() {
        for (Participant participant : participants.getParticipants()) {
            handOutCardTo(participant, INITIAL_CARD_AMOUNT);
        }
    }

    public void handOutCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receive(card);
    }

    public void handOutCardTo(Participant participant, int amount) {
        for (int i = 0; i < amount; i++) {
            handOutCardTo(participant);
        }
    }

    public void handOutAdditionalCardToDealer() {
        Participant dealer = getDealer();
        while (dealer.isAbleToReceiveCard()) {
            handOutCardTo(dealer);
        }
    }

    public int getParticipantHitCardCount(Participant participant) {
        return participant.getCards().size() - INITIAL_CARD_AMOUNT;
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }

    public Participants getParticipants() {
        return participants;
    }
}
