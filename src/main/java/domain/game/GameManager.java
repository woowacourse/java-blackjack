package domain.game;

import domain.card.Card;
import domain.participant.Dealer;
import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

public class GameManager {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public GameManager(Players players) {
        this.dealer = new Dealer();
        this.players = players;
        this.deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void distributeInitialCards() {
        distributeInitialCards(dealer);
        distributeCardToPlayers(players);
    }

    private void distributeCardToPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            distributeInitialCards(player);
        }
    }

    private void distributeInitialCards(Participant participant) {
        drawCardTo(participant);
        drawCardTo(participant);
    }

    public void drawCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receiveCard(card);
    }
}
