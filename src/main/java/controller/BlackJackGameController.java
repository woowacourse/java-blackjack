package controller;

import domain.*;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameController {

    public BlackJackGameController() {
    }

    public void run() {
        List<Player> players = initPlayer();
        Dealer dealer = initDealer();
        Deck deck = new Deck();

        // 초기 카드 분배 (2장)
        for (Player player : players) {
            Card card1 = deck.distributeCard();
            player.receiveCard(card1);
            Card card2 = deck.distributeCard();
            player.receiveCard(card2);
        }
        Card card1 = deck.distributeCard();
        dealer.receiveCard(card1);
        Card card2 = deck.distributeCard();
        dealer.receiveCard(card2);

    }

    private List<Player> initPlayer() {
        List<Player> players = new ArrayList<>();
        List<String> playerNames = getPlayerNames();

        for (String name : playerNames) {
            Name playerName = new Name(name);
            players.add(new Player(playerName));
        }
        return players;
    }

    private Dealer initDealer() {
        Name name = new Name("딜러");
        Dealer dealer = new Dealer(name);
        return dealer;
    }

    private List<String> getPlayerNames() {
        // return InputView.~~ (List<String> 반환)
    }

}
