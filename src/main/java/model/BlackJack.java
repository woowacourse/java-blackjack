package model;

import model.player.Players;

public class BlackJack {

    private final Players players;


    public BlackJack(Players players) {
        this.players = players;
    }

    public void offerCardToPlayers(int cardCount) {
        players.offerCardToPlayers(cardCount);
    }
}
