package domain;

import java.util.List;

public class Game {

    private final Deck deck;

    private final Players players;

    public Game(List<Player> players, Deck deck, Dealer dealer) {
        this.players = new Players(players, dealer);
        this.deck = deck;
    }

    public void dealTwoCards() {
        for (int i = 0; i < 2; i++) {
            players.dealCardsFrom(deck);
        }
    }

    public Result getResult(String name) {
        return players.getUserResult(name);
    }

    public List<Result> getDealerResults() {
        return players.getDealerResults();
    }

    public void dealCard(String name) {
        Player player = players.findUserByName(name);
        player.addCard(deck.drawCard());
    }

    public boolean canHit(String name) {
        return players.findUserByName(name).canHit();
    }

    public void dealCardToDealer() {
        players.dealCardToDealer(deck);
    }

    public List<Card> getCards(String name) {
        return players.findUserByName(name).getCards();
    }

    public List<Player> getUsers() {
        return players.getUsers();
    }

    public Players getPlayers() {
        return players;
    }
}
