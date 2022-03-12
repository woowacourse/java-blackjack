package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participants;

public class BlackjackGame {

    private final Deck deck = new Deck();
    private final Participants participants;

    public BlackjackGame(Participants participants) {
        this.participants = participants;
    }

    public void initCards() {
        participants.dealInitialCards(deck);
    }

    public void playPlayerTurn(TurnManager turnManager) {
        turnManager.getCurrentPlayer().addCard(deck.pickCard());
    }

    public int playDealerTurnAndReturnTurnCount() {
        int count = 0;
        while (participants.getDealer().checkMustHit()) {
            participants.getDealer().addCard(deck.pickCard());
            count++;
        }
        return count;
    }

    public Participants getParticipants() {
        return participants;
    }
}
