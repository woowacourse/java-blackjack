package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Dealer dealer;
    private List<Player> players;
    private Deck deck;

    public Game(List<String> playerNames) {
        if (playerNames == null || playerNames.isEmpty()) {
            throw new IllegalArgumentException("Player names cannot be null or empty");
        }
        dealer = new Dealer();
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void start() {
        for (Player player : players) {
            player.receiveCard(deck.dealCard());
            player.receiveCard(deck.dealCard());
        }
        dealer.receiveCard(deck.dealCard());
        dealer.receiveCard(deck.dealCard());
    }

    public void play() {
        for (Player player : players) {
            while (player.getCardsValue() < 21 && player.wantsCard()) {
                player.receiveCard(deck.dealCard());
            }
        }
        while (dealer.wantsCard()) {
            dealer.receiveCard(deck.dealCard());
        }
    }
}
