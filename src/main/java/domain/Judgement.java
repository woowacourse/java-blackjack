package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class Judgement {
    public Map<String, GameResult> judgePlayerResults(Players players, Dealer dealer) {
        Map<String, GameResult> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult gameResult = judge(player.calculateScore(), dealer.calculateScore());
            playerResults.put(player.getName(), gameResult);
        }
        return playerResults;
    }

    private GameResult judge(int playerScore, int dealerScore) {
        if (playerScore > 21 && dealerScore > 21) { return GameResult.DRAW; }
        if (playerScore > 21) { return GameResult.LOSE; }
        if (dealerScore > 21) { return GameResult.WIN; }
        if (playerScore > dealerScore) { return GameResult.WIN; }
        if (playerScore == dealerScore) { return GameResult.DRAW; }
        return GameResult.LOSE;
    }
}
