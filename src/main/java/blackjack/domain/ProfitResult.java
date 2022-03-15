package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

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
        return new ParticipantProfit(player.getName(), playerJudgement.calculateProfit(player.getBetMoney()));
    }

    private ParticipantProfit calculateDealerResult(List<ParticipantProfit> playersProfits, Dealer dealer) {
        int playersProfitSum = playersProfits.stream()
            .map(ParticipantProfit::getProfit)
            .mapToInt(Profit::getAmount)
            .sum();
        Profit dealerProfit = new Profit(playersProfitSum * -1);
        return new ParticipantProfit(dealer.getName(), dealerProfit);
    }

    public ParticipantProfit getDealerResult() {
        return dealerResult;
    }

    public List<ParticipantProfit> getPlayersResult() {
        return Collections.unmodifiableList(playersResult);
    }
}
