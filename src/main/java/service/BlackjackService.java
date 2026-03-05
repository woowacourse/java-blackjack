package service;

import java.util.ArrayList;
import java.util.List;

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
        dealer.addInitialedCard(cards);
    }

    public List<Player> createPlayers(List<String> names, Cards cards) {
        List<Player> playerList = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            player.addInitialedCard(cards);
            playerList.add(player);
        }
        return playerList;
    }

    public void determineAdditionalCard(Dealer dealer, Cards cards) {
        if (dealer.needAdditionalCard()) {
            dealer.add(cards.pop());
            OutputView.displayDealerCard();
        }
    }
}
