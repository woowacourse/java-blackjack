package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class Judgement {
    private static final int BLACKJACK_SCORE = 21;

    public Map<String, GameResult> judgePlayerResults(Players players, Dealer dealer) {
        Map<String, GameResult> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult gameResult = judge(player.calculateScore(), dealer.calculateScore());
            playerResults.put(player.getName(), gameResult);
        }
        return playerResults;
    }

    private GameResult judge(int playerScore, int dealerScore) {
        if (playerScore > BLACKJACK_SCORE && dealerScore > BLACKJACK_SCORE) { return GameResult.DRAW; }
        if (playerScore > BLACKJACK_SCORE) { return GameResult.LOSE; }
        if (dealerScore > BLACKJACK_SCORE) { return GameResult.WIN; }
        if (playerScore > dealerScore) { return GameResult.WIN; }
        if (playerScore == dealerScore) { return GameResult.DRAW; }
        return GameResult.LOSE;
    }
}
