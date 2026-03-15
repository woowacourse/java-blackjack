package service;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import utils.generator.CardGenerator;

public class BlackjackService {

    public Deck generateCards() {
        Deck deck = CardGenerator.generate();
        deck.shuffle();
        return deck;
    }

    public void distributeInitialCardsForPlayers(Players players, Deck deck) {
        for (Player player : players) {
            player.receiveInitialCards(deck);
        }
    }

    public Dealer createDealer(Deck deck) {
        Dealer dealer = new Dealer(Dealer.DEALER_NAME);
        giveInitialCards(deck, dealer);
        return dealer;
    }

    private void giveInitialCards(Deck deck, Dealer dealer) {
        dealer.receiveInitialCards(deck);
    }

    public int determineAdditionalCardOfDealer(Dealer dealer, Deck deck) {
        int additionalCardCount = 0;
        while (dealer.needAdditionalCard()) {
            dealer.add(deck.pop());
            additionalCardCount++;
        }
        return additionalCardCount;
    }
}
