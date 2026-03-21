package domain.betting;

import domain.participant.Name;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BettingTable {
    private final List<PlayerBet> playerBets;

    private BettingTable(List<PlayerBet> playerBets) {
        this.playerBets = playerBets;
    }

    public static BettingTable create() {
        List<PlayerBet> bettingTable = new ArrayList<>();
        return new BettingTable(bettingTable);
    }

    public void place(PlayerBet playerBet) {
        playerBets.add(playerBet);
    }

    public Settlement settle(List<PlayerShowdownResult> playersBettingResult) {
        Map<Name, Integer> playerProfits = calculatePlayersProfit(playersBettingResult);
        int dealerProfit = calculateDealerProfit(playerProfits);

        return new Settlement(dealerProfit, playerProfits);
    }

    private Map<Name, Integer> calculatePlayersProfit(List<PlayerShowdownResult> playersBettingResult) {
        Map<Name, Integer> playerProfits = new LinkedHashMap<>();
        for (PlayerShowdownResult result : playersBettingResult) {
            addPlayerProfit(playerProfits, result);
        }
        return playerProfits;
    }

    private void addPlayerProfit(Map<Name, Integer> playerProfits, PlayerShowdownResult result) {
        Name name = result.playerName();
        MatchResult matchResult = result.matchResult();
        BettingAmount bettingAmount = findBetByName(name).bettingAmount();

        int profit = matchResult.calculateProfit(bettingAmount);
        playerProfits.put(name, profit);
    }

    private int calculateDealerProfit(Map<Name, Integer> playerProfits) {
        int totalPlayerProfit = 0;
        for (Integer profit : playerProfits.values()) {
            totalPlayerProfit += profit;
        }
        return Math.negateExact(totalPlayerProfit);
    }

    private PlayerBet findBetByName(Name targetName) {
        return playerBets.stream()
                .filter(currentBetting -> currentBetting.playerName().equals(targetName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("배팅 정보 찾기 오류"));
    }
}