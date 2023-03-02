package service;

import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.List;

public class BlackjackService {

    private static final String HIT = "y";
    private static final String STAND = "n";

    private final Deck deck;
    private final Participants participants;

    private BlackjackService(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static BlackjackService of(List<String> playersName) {
        return new BlackjackService(Deck.create(), Participants.of(playersName));
    }

    public void start() {
        participants.initHand(deck);
    }

    public boolean existNextPlayerTurn() {
        return participants.getNextTurnPlayer().isPresent();
    }

    public void nextTurn(String hit) {
        Participant nextPlayer = participants.getNextTurnPlayer().get();

        if (hit.equals(STAND)) {
            nextPlayer.stand();
            return;
        }

        if (hit.equals(HIT)) {
            nextPlayer.addCard(deck.pollAvailableCard());
        }
    }

}
