package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> users;
    private final Dealer dealer;

    public Players(List<Player> users, Dealer dealer) {
        this.users = users;
        this.dealer = dealer;
    }

    public Player findUserByName(String name) {
        return users.stream()
                .filter(user -> user.hasName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다"));
    }

    public void dealCardsFrom(Deck deck) {
        for (Player user : users) {
            user.addCard(deck.drawCard());
        }
        dealCardToDealer(deck);
    }

    public void dealCardToDealer(Deck deck) {
        dealer.addCard(deck.drawCard());
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

    public Result getUserResult(String name) {
        Player player = findUserByName(name);
        return getResult(player, dealer);
    }

    public List<Result> getDealerResults() {
        List<Result> results = new ArrayList<>();

        for (Player player : users) {
            results.add(getResult(dealer, player));
        }
        return results;
    }

    public List<Player> getUsers() {
        return users;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
