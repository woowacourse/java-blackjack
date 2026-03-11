package model;

import java.util.List;
import model.card.Deck;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int INITIAL_DISPENSE_COUNT = 2;

    private final Deck deck;

    public Dealer(Deck deck) {
        super(DEALER_NAME);
        this.deck = deck;
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() <= DEALER_HIT_THRESHOLD;
    }

    public void distributeInitialCards(List<Player> players) {
        distributeInitialByParticipant(this);
        players.forEach(this::distributeInitialByParticipant);
    }

    public void distributeCard(Player player) {
        player.addCard(deck.draw());
    }

    public void distributeCard() {
        this.addCard(deck.draw());
    }

    private void distributeInitialByParticipant(Participant participant) {
        for (int i = 0; i < INITIAL_DISPENSE_COUNT; i++) {
            participant.addCard(deck.draw());
        }
    }
}
