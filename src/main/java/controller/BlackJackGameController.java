package controller;

import domain.*;
import view.InputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        for (Player player : players) {
            while (player.canReceiveCard()) {
                if (!isContinue(InputView.askContinue(player.getName()))) {
                    break;
                }
                Card card = deck.distributeCard();
                player.receiveCard(card);
            }
        }

        while (dealer.canReceiveCard()) {
            if (!dealer.canReceiveCard()) {
                break;
            }
            Card card = deck.distributeCard();
            dealer.receiveCard(card);
        }

        Map<Participant, Integer> participantScores = new HashMap<>();
        participantScores.put(dealer, dealer.getScore());

        for(Player player : players) {
            participantScores.put(player, player.getScore());
        }

        Map<String, Boolean> gameResult = Result.calculateResult(participantScores);
    }

    private boolean isContinue(String response) {
        if (response.equals("y")) {
            return true;
        }

        return false;
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
        return null;
    }

}
