package blackjack.domain.user;

import blackjack.domain.card.Cards;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players create(Map<String, Money> playerBets, Map<String, Cards> playerCards) {
        return playerBets.entrySet().stream()
                .map(entry -> {
                    String name = entry.getKey();
                    return new Player(name, entry.getValue(), playerCards.get(name));
                })
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Players::new
                ));
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public Map<String, Double> getStatistics(Dealer dealer) {
        Map<String, Double> playerProfit = new LinkedHashMap<>();
        double dealerProfit= 0;
        for (Player player : players) {
            double profit = player.calculateProfit(dealer);
            dealerProfit-=profit;
            playerProfit.put(player.getName(), profit);
        }
        playerProfit.put(dealer.getName(), dealerProfit);
        return playerProfit;
    }
}
