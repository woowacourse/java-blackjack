package blackjack.domain.rule;

import blackjack.domain.Result;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Judge {

    private final Map<Participant, Result> results;

    public Judge(Participants participants) {
        this.results = new LinkedHashMap<>();
        judgePlayers(participants.getPlayers(), participants.getDealer());
        judgeDealer(participants.getDealer());
    }

    private void judgePlayers(Players players, Dealer dealer) {
        Score dealerScore = dealer.getHandScore();
        for (Player player : players.getPlayers()) {
            Result playerResult = judgePlayer(player.getHandScore(), dealerScore);
            results.put(player, playerResult);
        }
    }

    private Result judgePlayer(Score playerScore, Score dealerScore) {
        if (playerScore.isBust()) {
            return Result.createLossResult();
        }
        if (dealerScore.isBust()) {
            return Result.createWinResult();
        }

        if (playerScore.isBiggerThan(dealerScore)) {
            return Result.createWinResult();
        }
        return Result.createLossResult();
    }

    private void judgeDealer(Dealer dealer) {
        List<Result> playerResults = results.values().stream().toList();
        Result result = Result.create(playerResults);
        results.put(dealer, result);
    }

    public Map<Participant, Result> getResults() {
        return results;
    }
}
