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

    public Result getResult(Player player) {
        return players.getUserResult(player);
    }

    public void dealCard(Player player) {
        player.addCard(deck.drawCard());
    }

    public boolean canHitByPlayerScore(Player player) {
        return player.canHit();
    }

    public boolean canHitByDealerScore() {
        return canHitByPlayerScore(players.getDealer());
    }

    public void dealCardToDealer() {
        Dealer dealer = players.getDealer();

        if (dealer.canHit()) {
            dealer.addCard(deck.drawCard());
        }
    }

    public List<Player> getUsers() {
        return players.getUsers();
    }

    public Players getPlayers() {
        return players;
    }

}
