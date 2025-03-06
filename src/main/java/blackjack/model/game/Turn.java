package blackjack.model.game;

import blackjack.model.card.Card;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import java.util.Deque;
import java.util.LinkedList;

public class Turn {
    private final Deque<Participant> readyQueue;

    public Turn(Participants participants) {
        this.readyQueue = new LinkedList<>(participants.getParticipants());
    }

    public void drawCardCurrentTurnParticipant(Card card) {
        readyQueue.getFirst().putCard(card);
    }

    public void changeTurn() {
        readyQueue.poll();
    }

    public Participant getCurrentTurnParticipant() {
        return readyQueue.getFirst();
    }

    public boolean hasReadyParticipant() {
        return !readyQueue.isEmpty();
    }

    public void skipTurn() {
        readyQueue.poll();
    }
}
