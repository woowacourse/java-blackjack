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

    public List<Player> getPlayers() {
        return users.stream()
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .toList();
    }

    public Dealer getDealer() {
        return users.stream()
                .filter(Dealer.class::isInstance)
                .map(Dealer.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_DEALER_NOT_FOUND));
    }

    public ProfitResult determineWinner(BetAmounts betAmounts) {
        Map<Player, Integer> result = new HashMap<>();
        List<Player> players = getPlayers();
        Dealer dealer = getDealer();

        for (Player player : players) {
            GameResult gameResult = GameResult.judge(dealer, player);
            int profit = betAmounts.calculateProfit(player, gameResult);
            result.put(player, profit);
        }
        int dealerPayout = -getTotalPlayerProfit(result);

        return new ProfitResult(result, dealerPayout);
    }

    private static int getTotalPlayerProfit(Map<Player, Integer> result) {
        return result.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
