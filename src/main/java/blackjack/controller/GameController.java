package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.Name;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.awt.desktop.AboutEvent;
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

        // 딜러 카드추가


        // 카드 받을지 물어보는 기능
        for (Player player : players.getCardNeedPlayers()) {
            questionOneMoreCard(player);
        }
    }

    public void questionOneMoreCard(Player player) {
        boolean flag = true;
        while (player.isOneMoreCard()) {
            if (!InputView.inputOneMoreCard(player.getName())) {
                if(flag){
                    OutputView.printPlayerCardState(player);
                }
                break;
            }
            player.addCard(CardDeck.giveCard());
            OutputView.printPlayerCardState(player);
            flag = false;
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