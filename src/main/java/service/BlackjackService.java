package service;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import utils.generator.CardGenerator;

import java.util.ArrayList;
import java.util.List;

public class BlackjackService {

    public Deck generateCards() {
        Deck deck = CardGenerator.generate();
        deck.shuffle();
        return deck;
    }

    public void giveInitialCards(Deck deck, Dealer dealer) {
        dealer.receiveInitialCards(deck);
    }

    public Players createPlayers(List<String> names, Deck deck) {
        List<Player> playerList = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            player.receiveInitialCards(deck);
            playerList.add(player);
        }
        return new Players(playerList);
    }

    public Dealer createDealer(Deck deck) {
        Dealer dealer = new Dealer(Dealer.DEALER_NAME);
        giveInitialCards(deck, dealer);
        return dealer;
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
