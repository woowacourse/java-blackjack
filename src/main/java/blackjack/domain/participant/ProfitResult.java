package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProfitResult {

    private final ParticipantProfit dealerResult;
    private final List<ParticipantProfit> playersResult;

    public ProfitResult(List<Player> players, Dealer dealer) {
        players = new ArrayList<>(players);
        List<ParticipantProfit> playersResult = calculatePlayersResult(players, dealer);
        this.dealerResult = calculateDealerResult(playersResult, dealer);
        this.playersResult = playersResult;
    }

    private List<ParticipantProfit> calculatePlayersResult(List<Player> players, Dealer dealer) {
        return players.stream()
            .map(player -> calculateParticipantProfit(player, dealer))
            .collect(Collectors.toList());
    }

    private ParticipantProfit calculateParticipantProfit(Player player, Dealer dealer) {
        Judgement playerJudgement = Judgement.judgePlayer(player, dealer);
        BetMoney betMoney = player.getBetMoney();
        double profitMultiple = playerJudgement.getProfitMultiple();
        return new ParticipantProfit(player.getName(), betMoney.calculateProfit(profitMultiple));
    }

    private ParticipantProfit calculateDealerResult(List<ParticipantProfit> playersProfits, Dealer dealer) {
        int playersProfitSum = playersProfits.stream()
            .mapToInt(ParticipantProfit::getProfit)
            .sum();
        return new ParticipantProfit(dealer.getName(), playersProfitSum * -1);
    }

    public ParticipantProfit getDealerResult() {
        return dealerResult;
    }

    public List<ParticipantProfit> getPlayersResult() {
        return Collections.unmodifiableList(playersResult);
    }
}
