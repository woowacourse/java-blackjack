package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
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
        return Map.copyOf(results);
    }
}
