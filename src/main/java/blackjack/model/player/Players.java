package blackjack.model.player;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.model.BettingMoney;
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

    public Map<Player, BigDecimal> calculateWinnings(final Map<Player, Map<GameResult, Integer>> gameResults) {
        Map<Player, BigDecimal> winnings = createPlayersWinnings();
        BigDecimal dealerWinnings = winnings.get(dealer);
        for (User user : users) {
            BigDecimal playerWinnings = calculateWinnings(user, gameResults.get(user), user.getBettingMoney());
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

    private BigDecimal calculateWinnings(final Player player, final Map<GameResult, Integer> results,
                                         final BettingMoney bettingMoney) {
        GameResult gameResult = results.keySet()
                .iterator()
                .next();
        return computePlayerWinning(player, gameResult, bettingMoney);
    }

    private BigDecimal computePlayerWinning(
            final Player player, final GameResult result, final BettingMoney bettingMoney
    ) {
        if (result == GameResult.WIN && player.isBlackjack()) {
            return bettingMoney.getAmount().multiply(BigDecimal.valueOf(1.5));
        }
        if (result == GameResult.WIN) {
            return bettingMoney.getAmount();
        }
        if (result == GameResult.LOSE) {
            return bettingMoney.getAmount().negate();
        }
        return BigDecimal.ZERO;
    }

}
