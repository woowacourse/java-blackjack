package model.paticipant;

import model.card.Deck;

public class GameManager {

    private static final int INITIAL_DISPENSE_COUNT = 2;
    private static final int BASE_DISPENSE_COUNT = 1;

    private final Deck deck;

    public GameManager(Deck deck) {
        this.deck = deck;
    }

    public void distributeInitialCards(Dealer dealer, Players players) {
        distributeInitialByParticipant(dealer, INITIAL_DISPENSE_COUNT);
        players.forEach(player -> distributeInitialByParticipant(player, INITIAL_DISPENSE_COUNT));
    }

    public void distributeCard(Participant participant) {
        distributeInitialByParticipant(participant, BASE_DISPENSE_COUNT);
    }

    private void distributeInitialByParticipant(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            participant.addCard(deck.draw());
        }
    }
}
