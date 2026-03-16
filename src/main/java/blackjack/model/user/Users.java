package blackjack.model.user;

import blackjack.model.bet.BetAmounts;
import blackjack.model.gameresult.GameResult;
import blackjack.model.gameresult.ProfitResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Users {

    private static final String ERROR_DEALER_NOT_FOUND = "딜러가 존재하지 않습니다.";

    private final List<User> users;

    public Users(List<Player> players, Dealer dealer) {
        users = new ArrayList<>(players);
        users.add(dealer);
    }

    public Users(String playerNames) {
        users = new ArrayList<>(PlayerParser.parse(playerNames));
        users.add(new Dealer());
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }

    public List<User> getPlayers() {
        return users.stream()
                .filter(User::isPlayer)
                .toList();
    }

    public User getDealer() {
        return users.stream()
                .filter(user -> !user.isPlayer())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_DEALER_NOT_FOUND));
    }

//    public List<Player> getPlayers() {
//        return users.stream()
//                .filter(Player.class::isInstance)
//                .map(Player.class::cast)
//                .toList();
//    }
//
//    public Dealer getDealer() {
//        return users.stream()
//                .filter(Dealer.class::isInstance)
//                .map(Dealer.class::cast)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(ERROR_DEALER_NOT_FOUND));
//    }

    public ProfitResult determineWinner(BetAmounts betAmounts) {
        Map<User, Integer> result = new HashMap<>();
        List<User> players = getPlayers();
        User dealer = getDealer();

        for (User player : players) {
            GameResult gameResult = player.judge(dealer);
            int profit = gameResult.calculateProfit(betAmounts.findByUser(player));
            result.put(player, profit);
        }
        int dealerPayout = -getTotalPlayerProfit(result);

        return new ProfitResult(result, dealerPayout);
    }

    private static int getTotalPlayerProfit(Map<User, Integer> result) {
        return result.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
//    public ProfitResult determineWinner(BetAmounts betAmounts) {
//        Map<Player, Integer> result = new HashMap<>();
//        List<Player> players = getPlayers();
//        Dealer dealer = getDealer();
//
//        for (Player player : players) {
//            GameResult gameResult = player.judge(dealer);
//            int profit = gameResult.calculateProfit(betAmounts.findByPlayer(player));
//            result.put(player, profit);
//        }
//        int dealerPayout = -getTotalPlayerProfit(result);
//
//        return new ProfitResult(result, dealerPayout);
//    }
//
//    private static int getTotalPlayerProfit(Map<Player, Integer> result) {
//        return result.values().stream()
//                .mapToInt(Integer::intValue)
//                .sum();
//    }
}
