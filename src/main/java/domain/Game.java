package domain;

import java.util.List;

public class Game {

    private final Deck deck;
    private final Players players;

    public Game(List<Player> players, Deck deck, Dealer dealer) {
        this.players = new Players(players, dealer);
        this.deck = deck;
    }

    public void dealCardsTwice() {
        for (int i = 0; i < 2; i++) {
            players.dealCardsFrom(deck);
        }
    }

    public void dealCardTo(String name) {
        Player player = players.findUserByName(name);
        player.addCard(deck.draw());
    }

    public boolean dealCardToDealer() {
        Dealer dealer = players.getDealer();
        if (dealer.canHit()) {
            dealer.addCard(deck.draw());
            return true;
        }
        return false;
    }

    public Result getResult(String name) {
        Player player = players.findUserByName(name);
        Dealer dealer = players.getDealer();
        return player.competeWith(dealer);
    }

    public List<Result> getDealerResults() {
        return players.getDealerResults();
    }

    public List<Player> getUsers() {
        return players.getUsers();
    }

    public Players getPlayers() {
        return players;
    }
}
