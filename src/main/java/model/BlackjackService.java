package model;

import model.card.Deck;
import model.paticipant.Dealer;
import model.paticipant.Participant;
import model.paticipant.Players;

public class BlackjackService {

    private static final int INITIAL_DISPENSE_COUNT = 2;
    private static final int BASE_DISPENSE_COUNT = 1;

    private final Deck deck;

    public BlackjackService(Deck deck) {
        this.deck = deck;
    }

    public void drawTwoCards(Dealer dealer, Players players) {
        drawCardByParticipant(dealer, INITIAL_DISPENSE_COUNT);
        players.forEach(player -> drawCardByParticipant(player, INITIAL_DISPENSE_COUNT));
    }

    public void drawOneCard(Participant participant) {
        drawCardByParticipant(participant, BASE_DISPENSE_COUNT);
    }

    private void drawCardByParticipant(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            participant.addCard(deck.draw());
        }
    }
}
