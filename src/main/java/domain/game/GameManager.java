package domain.game;

import domain.card.Card;
import domain.participant.Dealer;
import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.Players;

public class GameManager {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public GameManager(Players players) {
        this.dealer = initDealer();
        this.players = players;
        this.deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    private Dealer initDealer() {
        Dealer dealer = new Dealer();
        return dealer;
    }

    public void distributeInitialCards() {
        distributeCardToDealer(dealer);
        distributeCardToPlayers(players);
    }

    private void distributeCardToDealer(Dealer dealer) {
        distributeInitialCards(dealer);
    }

    private void distributeCardToPlayers(Players players) {
        players.forEach(this::distributeInitialCards);
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
