package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.Name;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import blackjack.view.InputView;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    public GameController() {

    }

    public void run() {
        String[] names = InputView.inputPlayerName();
        System.out.println(names[0] + names[1]);
        List<Player> playerList = new ArrayList<>();
        for (String name : names) {
            playerList.add(Player.of(Name.of(name)));
        }
        Players players = Players.of(playerList);
        // 카드 2장 지급
        Dealer dealer = Dealer.of();
        dealer.addCard(CardDeck.giveCard());
        // 52장 Deck ->
        // 카드 받을지 물어보는 기능
        boolean moreCard = InputView.inputOneMoreCard("jack");
        System.out.println(moreCard);
    }
}