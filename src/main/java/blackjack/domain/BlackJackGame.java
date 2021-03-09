package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

public class BlackJackGame {

    private Participants participants;
    private Deck deck;
    private int currentPlayerIdx;

    public BlackJackGame(Deck deck, Participants participants) {
        this.participants = participants;
        this.deck = deck;
        this.currentPlayerIdx = 0;
    }

    public Participants setupInitialCards() {
        participants.dealCardsAllParticipants(deck);
        return participants;
    }


    public void turn() {
        if (isEnd()) {
            return;
        }
        currentPlayerIdx++;
    }

    public Participants getParticipants() {
        return participants;
    }

    public Participant getCurrentParticipant() {
        return participants.getParticipant(currentPlayerIdx);
    }

    public boolean isEnd() {
        return currentPlayerIdx >= participants.size();
    }
}
