package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class Bettings {

    private final Map<String, Integer> bettings;

    public Bettings() {
        this.bettings = new HashMap<>();
    }

    public void registerBetting(String playerName, int bettingMoney) {
        this.bettings.put(playerName, bettingMoney);
    }

    public int findBettingOfPlayer(String playerName) {
        return this.bettings.get(playerName);
    }

    public void updateBetting(Dealer dealer, Player player) {
        int initialBetting = findBettingOfPlayer(player.getName());
        GameStatus gameStatus = determineGameStatus(dealer, player);
        int betting  = (int) (initialBetting * gameStatus.getProfiteRate());
        registerBetting(player.getName(), betting);
    }

    public int calculateDealerBetting() {
        return this.bettings.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum() * (-1);
    }

    private GameStatus determineGameStatus(Dealer dealer, Player player) {
        if (dealer.isBust()) {
            return GameStatus.WIN;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return GameStatus.TIE;
        }
        if (player.isBlackjack()) {
            return GameStatus.BLACKJACK;
        }
        if (player.isBust()) {
            return GameStatus.LOSE;
        }
        return player.determineGameStatusByScore(dealer);
    }
}
