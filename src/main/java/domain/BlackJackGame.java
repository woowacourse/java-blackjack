package domain;

import java.util.List;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Deck deck;
    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(Deck deck, List<Player> players, Dealer dealer) {
        this.deck = deck;
        this.players = players;
        this.dealer = dealer;
    }

    public void distributeInitialCards() {
        distributeInitialCard(dealer);
        players.forEach(this::distributeInitialCard);
    }

    public void playGameWithDealer() {
        distributeCard(dealer);
    }

    public void playGameWithPlayer(Player player) {
        distributeCard(player);
    }

    private void distributeInitialCard(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            distributeCard(participant);
        }
    }

    private void distributeCard(Participant participant) {
        Card card = deck.distributeCard();
        participant.receiveCard(card);
    }
}
