package model;

import java.util.List;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int MINIMUM_STAND_SCORE = 16;
    private static final int INITIAL_DISPENSE_COUNT = 2;

    private final Cards deck;

    public Dealer(Cards deck) {
        super(DEALER_NAME);
        this.deck = deck.shuffle();
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
        player.addCard(deck.pickCard());
    }

    public void distributeCard() {
        this.addCard(deck.pickCard());
    }

    private void distributeInitialByPlayer(Player player) {
        for (int i = 0; i < INITIAL_DISPENSE_COUNT; i++) {
            player.addCard(deck.pickCard());
        }
    }
}
