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
        dealer.addCard(deck.drawCard());
    }

    public Result getUserResult(String name) {
        Player player = findUserByName(name);
        return player.compare(dealer);
    }

    public List<Result> getDealerResults() {
        List<Result> results = new ArrayList<>();

        for (Player player : users) {
            results.add(dealer.compare(player));
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
