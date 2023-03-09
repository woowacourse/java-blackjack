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
            handOutCardTo(participant);
            handOutCardTo(participant);
        }
    }

    private void handOutCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receive(card);
    }

    public void playByAction(Participant participant, BlackjackAction blackjackAction) {
        assertParticipation(participant);

        if (isAbleToContinue(participant, blackjackAction)) {
            handOutCardTo(participant);
        }
    }

    public boolean isAbleToContinue(Participant participant, BlackjackAction blackjackAction) {
        assertParticipation(participant);

        return participant.isAbleToReceiveCard()
                && blackjackAction == BlackjackAction.HIT;
    }

    public void handOutAdditionalCardToDealer() {
        Participant dealer = getDealer();
        while (dealer.isAbleToReceiveCard()) {
            handOutCardTo(dealer);
        }
    }

    private void assertParticipation(Participant participant) {
        if (participants.getParticipants().contains(participant)) {
            return;
        }

        throw new IllegalStateException();
    }

    public int getParticipantHitCardCount(Participant participant) {
        assertParticipation(participant);
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
