package service;

import java.util.ArrayList;
import java.util.List;

import constant.GameConstant;
import controller.BlackjackController;
import domain.Cards;
import domain.Dealer;
import domain.Player;
import utils.generator.CardGenerator;

public class BlackjackService {

    public Cards generateCards() {
        Cards cards = CardGenerator.generate();
        cards.shuffle();
        return cards;
    }

    public void giveInitialCards(Cards deck, Dealer dealer) {
        dealer.receiveInitialCards(deck);
    }

    public List<Player> createPlayers(List<String> names, Cards deck) {
        List<Player> playerList = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            player.receiveInitialCards(deck);
            playerList.add(player);
        }
        return playerList;
    }

    public Dealer createDealer(Cards deck) {
        Dealer dealer = new Dealer(GameConstant.DEALER_NAME);
        giveInitialCards(deck, dealer);
        return dealer;
    }

    public int determineAdditionalCardOfDealer(Dealer dealer, Cards cards) {
        int additionalCardCount = 0;
        while (dealer.needAdditionalCard()) {
            dealer.add(cards.pop());
            additionalCardCount++;
        }
        return additionalCardCount;
    }
}
