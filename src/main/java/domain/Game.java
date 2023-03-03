package domain;

import java.util.ArrayList;
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

    public void dealTwoCards() {
        for (int i = 0; i < 2; i++) {
            dealCards();
        }
    }

    private void dealCards() {
        for (Player player : players) {
            player.addCard(deck.drawCard());
        }

        dealer.addCard(deck.drawCard());
    }

    public Result isWon(String name) {
        Player player = findByName(name);
        return getResult(player, dealer);
    }

    private Player findByName(String name) {
        return players.stream()
                .filter(player -> player.hasName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다"));
    }

    private Result getResult(Player player, Player other) {
        if (player.getScore() == other.getScore() || (player.isBusted() && other.isBusted())) {
            return Result.DRAW;
        }

        if (!player.isBusted() && (player.getScore() > other.getScore() || other.isBusted())) {
            return Result.WIN;
        }

        return Result.LOSE;
    }

    public List<Result> getDealerResults() {
        List<Result> results = new ArrayList<>();

        for (Player player : players) {
            results.add(getResult(dealer, player));
        }

        return results;
    }

    public void dealAnotherCard(String name) {
        Player player = findByName(name);
        player.addCard(deck.drawCard());
    }

    public boolean canHit(String name) {
        return findByName(name).canHit();
    }

    public void dealAnotherCard() {
        dealer.addCard(deck.drawCard());
    }

    public List<Card> getCards(String name) {
        return findByName(name).getCards();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
