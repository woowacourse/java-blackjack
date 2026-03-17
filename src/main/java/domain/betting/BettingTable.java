package domain.betting;

import domain.participant.Name;
import domain.participant.PlayerBettingResult;
import java.util.ArrayList;
import java.util.List;

public class BettingTable {
    private final List<PlayerBetting> bettingTable;

    private BettingTable(List<PlayerBetting> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public static BettingTable create() {
        List<PlayerBetting> bettingTable = new ArrayList<>();
        return new BettingTable(bettingTable);
    }

    public void add(PlayerBetting playerBetting) {
        bettingTable.add(playerBetting);
    }

    public ParticipantsProfitResult calculateAllParticipantsProfit(List<PlayerBettingResult> playersBettingResult) {
        List<PlayerProfitResult> playersProfitResult = calculatePlayersProfit(playersBettingResult);
        int dealerProfit = calculateDealerProfit(playersProfitResult);

        return new ParticipantsProfitResult(dealerProfit, playersProfitResult);
    }

    private List<PlayerProfitResult> calculatePlayersProfit(List<PlayerBettingResult> playersBettingResult) {
        return playersBettingResult.stream()
                .map(this::calculatePlayerProfit)
                .toList();
    }

    private PlayerProfitResult calculatePlayerProfit(PlayerBettingResult playerBettingResult) {
        Name name = playerBettingResult.playerName();
        BettingRule bettingRule = playerBettingResult.bettingRule();
        BettingAmount bettingAmount = findBettingByName(name).bettingAmount();

        int playerProfit = bettingRule.calculateProfit(bettingAmount);
        return new PlayerProfitResult(name.value(), playerProfit);
    }

    private int calculateDealerProfit(List<PlayerProfitResult> playersProfitResult) {
        return (-1) * playersProfitResult.stream()
                .mapToInt(PlayerProfitResult::profit)
                .sum();
    }

    private PlayerBetting findBettingByName(Name targetName) {
        return bettingTable.stream()
                .filter(currentBetting -> currentBetting.playerName().equals(targetName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("배팅 정보 찾기 오류"));
    }

}
