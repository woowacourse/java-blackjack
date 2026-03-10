package service;

import java.util.ArrayList;
import java.util.List;

import controller.BlackjackController;
import domain.Cards;
import domain.Dealer;
import domain.Player;
import utils.generator.CardGenerator;
import view.OutputView;

public class BlackjackService {

    public Cards generateCards() {
        Cards cards = CardGenerator.generate();
        cards.shuffle();
        return cards;
    }

    public void giveInitialedCard(Cards cards, Dealer dealer) {
        dealer.addInitializedCard(cards);
    }

    public List<Player> createPlayers(List<String> names, Cards cards) {
        List<Player> playerList = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            player.addInitializedCard(cards);
            playerList.add(player);
        }
        return playerList;
    }

    public Dealer createDealer(Cards cards) {
        Dealer dealer = new Dealer();
        giveInitialedCard(cards, dealer);
        return dealer;
    }

    public void determineAdditionalCardOfDealer(Dealer dealer, Cards cards) {
        while (dealer.needAdditionalCard()) {
            dealer.add(cards.pop());
            OutputView.displayDealerCard();
        }
    }
}
