package domain;

import java.util.List;

public class Game {

    private final List<Player> players;
    private final Deck deck;
    private final Dealer dealer;

    public Game(List<Player> players, Deck deck, Dealer dealer) {
        this.players = players;
        this.deck = deck;
        this.dealer = dealer;
    }

    public void dealCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.drawCard());
            }
        }
    }

    public Result isWon(int index) {
        Player player = players.get(index);
        if (player.getScore() == dealer.getScore() || (player.isBusted() && dealer.isBusted())) {
            return Result.DRAW;
        }
        if (player.getScore() > dealer.getScore() || dealer.isBusted()) {
            return Result.WIN;
        }

        return Result.LOSE;
    }

    public void dealAnotherCard(int index) {
        Player player = players.get(index);
        if (player.getScore() < 21) {
            player.addCard(deck.drawCard());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
