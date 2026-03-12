package blackjack.model.user;

import blackjack.model.BetAmounts;
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

    public List<User> getUsers() {
        return List.copyOf(users);
    }

    public ProfitResult determineWinner(BetAmounts betAmounts) {
        Map<Player, Integer> result = new HashMap<>();
        int dealerPayout = 0;
        List<Player> players = getPlayers();
        Dealer dealer = getDealer();

        for (Player player : players) {
            if (player.isBust()) {
                result.put(player, betAmounts.calculateLosePayout(player));
                dealerPayout += betAmounts.calculateWinPayout(player);
                continue;
            }

            if (player.isBlackjack() && dealer.isBlackjack()) {
                result.put(player, betAmounts.calculateDrawPayout());
                dealerPayout += betAmounts.calculateDrawPayout();
                continue;
            }

            if (player.isBlackjack()) {
                result.put(player, betAmounts.calculateBlackjackPayout(player));
                dealerPayout -= betAmounts.calculateBlackjackPayout(player);
                continue;
            }

            if (dealer.isBlackjack()) {
                result.put(player, betAmounts.calculateLosePayout(player));
                dealerPayout += betAmounts.calculateWinPayout(player);
                continue;
            }

            if (dealer.isBust()) {
                result.put(player, betAmounts.calculateWinPayout(player));
                dealerPayout -= betAmounts.calculateWinPayout(player);
                continue;
            }

            if (dealer.totalScore() == player.totalScore()) {
                result.put(player, betAmounts.calculateDrawPayout());
                dealerPayout += betAmounts.calculateDrawPayout();
                continue;
            }

            if (dealer.totalScore() > player.totalScore()) {
                result.put(player, betAmounts.calculateLosePayout(player));
                dealerPayout += betAmounts.calculateWinPayout(player);
                continue;
            }

            if (dealer.totalScore() < player.totalScore()) {
                result.put(player, betAmounts.calculateWinPayout(player));
                dealerPayout -= betAmounts.calculateWinPayout(player);
            }
        }

        return new ProfitResult(result, dealerPayout);
    }
}
