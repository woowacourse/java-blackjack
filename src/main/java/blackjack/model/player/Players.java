package blackjack.model.player;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.model.game.GameResult;

public class Players {

    private final Dealer dealer;
    private final List<User> users;

    public Players(final Dealer dealer, final List<User> users) {
        this.dealer = dealer;
        this.users = users;
    }

    public Players(final Dealer dealer, final Map<String, Integer> userNamesAndBettingAmount) {
        this(dealer, userNamesAndBettingAmount.entrySet()
                .stream()
                .map(entry -> new User(entry.getKey(), entry.getValue()))
                .toList());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<User> getUsers() {
        return users;
    }

    public Map<Player, BigDecimal> calculateWinnings(final Map<User, GameResult> gameResults) {
        Map<Player, BigDecimal> winnings = createPlayersWinnings();
        BigDecimal dealerWinnings = winnings.get(dealer);
        for (User user : gameResults.keySet()) {
            BigDecimal playerWinnings = calculateWinnings(user, gameResults.get(user));
            winnings.put(user, playerWinnings);
            dealerWinnings = dealerWinnings.subtract(playerWinnings);
        }
        winnings.put(dealer, dealerWinnings);
        return winnings;
    }

    private Map<Player, BigDecimal> createPlayersWinnings() {
        Map<Player, BigDecimal> winnings = new LinkedHashMap<>();
        winnings.put(dealer, BigDecimal.ZERO);
        return winnings;
    }

    private BigDecimal calculateWinnings(final User user, final GameResult result) {
        if (result == GameResult.BLACKJACK_WIN) {
            return user.calculateProfit(1.5);
        }
        if (result == GameResult.WIN) {
            return user.calculateProfit(1.0);
        }
        if (result == GameResult.LOSE) {
            return user.calculateProfit(-1.0);
        }
        return user.calculateProfit(0.0);
    }

}
