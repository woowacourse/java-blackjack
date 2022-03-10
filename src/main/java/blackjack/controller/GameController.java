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
        dealer.addCard(CardDeck.giveCard());
        players.giveCard();
        players.giveCard();

        // 카드 받을지 물어보는 기능
        for (Player player : players.getCardNeedPlayers()) {
            questionOneMoreCard(player);
            System.out.println(player.getCards().size());
        }

        // 플레이어 마다 물어봐야함.
    }

    public void questionOneMoreCard(Player player) {
        while (player.isOneMoreCard()) {
            if (!InputView.inputOneMoreCard(player.getName())) {
                break;
            }
            player.addCard(CardDeck.giveCard());
        }
    }

//    public void questionOneMoreCard(Player player) {
//        while (player.isOneMoreCard()) {
//            if (InputView.inputOneMoreCard(player.getName())) {
//                player.addCard(CardDeck.giveCard());
//                continue;
//            }
//            player.toNoCardNeeded();
//        }
//    }
}