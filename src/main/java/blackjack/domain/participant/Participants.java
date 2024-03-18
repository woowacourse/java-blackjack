package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participants {

    private static final int DEALER_PROFIT_MULTIPLIER = -1;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(List<String> playerNames, List<Money> playersMoney) {
        this.dealer = new Dealer();
        this.players = PlayerFactory.createPlayers(playerNames, playersMoney, dealer);
    }

    public Map<String, Double> createAllProfit() {
        Map<String, Double> allProfit = new LinkedHashMap<>();
        Map<String, Double> playersProfit = createPlayersProfit();
        allProfit.put(dealer.getName(), calculateDealerProfit(playersProfit));
        allProfit.putAll(playersProfit);

        return allProfit;
    }

    private Map<String, Double> createPlayersProfit() {
        Map<String, Double> playersProfit = new LinkedHashMap<>();

        for (Player player : players) {
            Money playerProfit = calculatePlayerProfit(player);
            playersProfit.put(player.getName(), playerProfit.getAmount());
        }

        return playersProfit;
    }

    private Money calculatePlayerProfit(Player player) {
        JudgeResult result = dealer.judgePlayer(player);

        return result.calculateProfit(player.getMoney());
    }

    private double calculateDealerProfit(Map<String, Double> playersProfitResult) {
        double totalPlayersProfit = playersProfitResult.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        return totalPlayersProfit * DEALER_PROFIT_MULTIPLIER;
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        return participants;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
