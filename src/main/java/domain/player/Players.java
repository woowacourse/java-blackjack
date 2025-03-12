package domain.player;

import domain.BattleResult;
import domain.card.Deck;
import domain.profit.NormalProfitStrategy;
import domain.profit.Profit;
import domain.profit.ProfitStrategy;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Players {

    private static final int DEALER_SIZE = 1;

    private final Dealer dealer;
    private final Users users;


    public Players(Dealer dealer, Users users) {
        this.dealer = dealer;
        this.users = users;
    }

    public void distributeInitialCards(Deck deck) {
        dealer.receiveInitialCards(deck);
        for (User user : users.getUsers()) {
            user.receiveInitialCards(deck);
        }
    }

    public void openInitialCards() {
        dealer.openInitialCards();
        for (User user : users.getUsers()) {
            user.openInitialCards();
        }
    }

    public Map<Player, Integer> computePlayerSum() {
        Map<Player, Integer> results = new LinkedHashMap<>();
        for (Player player : getPlayers()) {
            results.put(player, player.computeOptimalSum());
        }
        return results;
    }

    public Map<Dealer, Profit> computeDealerProfit() {
        var usersProfit = computeUsersProfit(NormalProfitStrategy.getInstance());
        Profit result = new Profit(
                usersProfit.values().stream()
                        .mapToInt(profit -> -profit.getProfit())
                        .sum()
        );
        return Map.of(dealer, result);
    }

    public Map<User, Profit> computeUsersProfit(ProfitStrategy profitStrategy) {
        var usersBattleResult = computeUsersBattleResult();
        return usersBattleResult.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey,
                        entry -> profitStrategy.calculateProfit(entry.getKey().getBet(), entry.getValue()),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    public Map<User, BattleResult> computeUsersBattleResult() {
        Map<User, BattleResult> results = new LinkedHashMap<>();
        for (User user : users.getUsers()) {
            results.put(user, BattleResult.fight(dealer, user));
        }
        return results;
    }

    public int size() {
        return DEALER_SIZE + users.size();
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>(List.of(dealer));
        players.addAll(users.getUsers());
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<User> getUsers() {
        return users.getUsers();
    }
}
