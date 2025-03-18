package model.participants;

import model.casino.Deck;

public class Participants {
    private PlayerGroup playerGroup;
    private Dealer dealer;

    public Participants(String playerNames) {
        playerGroup = new PlayerGroup(playerNames);
        dealer = new Dealer();
    }

    public void progressGame(Deck deck) {
        playerGroup.playersTurn(deck);
        dealer.dealersTurn(deck);
    }

    public PlayerGroup getPlayerGroup() {
        return playerGroup;
    }

    public Dealer getDealer() {
        return dealer;
    }

}
