package domain;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;

public class GameParticipant {
    private static final int INITIAL_DRAW_COUNT = 2;

    private Players players;
    private Dealer dealer;
    private CardDeck cardDeck;

    public GameParticipant(Players players) {
        this.players = players;
        this.dealer = new Dealer();
        this.cardDeck = new CardDeck();
    }

    public void initialDraw() {
        for (int i = 0; i < INITIAL_DRAW_COUNT; i++) {
            dealer.receive(cardDeck.draw());
        }
        for (int i = 0; i < INITIAL_DRAW_COUNT; i++) {
            giveOneCardToAllPlayers();
        }
    }

    private void giveOneCardToAllPlayers() {
        for (Player player : players.getPlayers()) {
            player.receive(cardDeck.draw());
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public Players getPlayers() {
        return players;
    }
}
