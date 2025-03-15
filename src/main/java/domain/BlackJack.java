package domain;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;

public class BlackJack {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackJack(final Players players, final Deck deck) {
        this.dealer = new Dealer();
        this.players = players;
        this.deck = deck;
    }

    public void prepareGame() {
        deck.shuffle();
        distributeInitialCards();
    }

    public void passAdditionalCard(Participant participant) {
        participant.receiveCard(deck.draw());
    }

    public void handleParticipantBust(Participant participant) {
        participant.applyBustPenalty();
    }

    private void distributeInitialCards() {
        dealer.receiveCard(deck.draw(), deck.draw());
        players.receiveInitialCards(deck);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
