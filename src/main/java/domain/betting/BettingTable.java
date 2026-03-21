package domain.betting;

import domain.participant.Name;
import java.util.ArrayList;
import java.util.List;
import view.dto.ParticipantsProfit;
import view.dto.PlayerProfit;

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

    public ParticipantsProfit calculateAllParticipantsProfit(List<PlayerShowdownResult> playersBettingResult) {
        List<PlayerProfit> playersProfitResult = calculatePlayersProfit(playersBettingResult);
        int dealerProfit = calculateDealerProfit(playersProfitResult);

        return new ParticipantsProfit(dealerProfit, playersProfitResult);
    }

    private List<PlayerProfit> calculatePlayersProfit(List<PlayerShowdownResult> playersBettingResult) {
        return playersBettingResult.stream()
                .map(this::calculatePlayerProfit)
                .toList();
    }

    private PlayerProfit calculatePlayerProfit(PlayerShowdownResult playerShowdownResult) {
        Name name = playerShowdownResult.playerName();
        MatchResult matchResult = playerShowdownResult.matchResult();
        BettingAmount bettingAmount = findBetByName(name).bettingAmount();

        int playerProfit = matchResult.calculateProfit(bettingAmount);
        return new PlayerProfit(name.value(), playerProfit);
    }

    private int calculateDealerProfit(List<PlayerProfit> playersProfitResult) {
        int playersTotalProfit = playersProfitResult.stream()
                .mapToInt(PlayerProfit::profit)
                .sum();

        return Math.negateExact(playersTotalProfit);
    }

    private PlayerBet findBetByName(Name targetName) {
        return playerBets.stream()
                .filter(currentBetting -> currentBetting.playerName().equals(targetName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("배팅 정보 찾기 오류"));
    }

}
