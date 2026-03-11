package model;

import java.util.List;
import model.card.Deck;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int MINIMUM_STAND_SCORE = 16;
    private static final int INITIAL_DISPENSE_COUNT = 2;

    private final Deck deck;

    public Dealer(Deck deck) {
        super(DEALER_NAME);
        this.deck = deck;
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() <= MINIMUM_STAND_SCORE;
    }

    public void distributeInitialCards(List<Player> players) {
        distributeInitialByPlayer(this);
        players.forEach(this::distributeInitialByPlayer);
    }

    public void distributeCard(Player player) {
        player.addCard(deck.draw());
    }

    public void distributeCard() {
        this.addCard(deck.draw());
    }

    private void distributeInitialByPlayer(Player player) {
        for (int i = 0; i < INITIAL_DISPENSE_COUNT; i++) {
            player.addCard(deck.draw());
        }
    }
}
