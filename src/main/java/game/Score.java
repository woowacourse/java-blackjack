package game;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import user.Dealer;
import user.Participant;
import user.Participants;

public class Score {
    private final Participants participants;

    public Score(Participants participants) {
        this.participants = participants;
    }

    public Map<Participant, GameResult> calculatePlayerScore() {
        Map<Participant, GameResult> gameResult = new LinkedHashMap<>();
        if (participants.getDealer().isBust()) {
            participants.getPlayers().forEach((user) -> putGameResultBust(user, gameResult));
            return gameResult;
        }
        participants.getPlayers().forEach((user) -> gameResult.put(user, compareScore(user)));
        return gameResult;
    }

    private void putGameResultBust(Participant participant, Map<Participant, GameResult> gameResult) {
        if (participant.isBust()) {
            gameResult.put(participant, GameResult.LOSE);
            return;
        }
        if (participant.isBlackjack()) {
            gameResult.put(participant, GameResult.BLACKJACK);
            return;
        }
        gameResult.put(participant, GameResult.WIN);
    }

    private GameResult compareScore(Participant player) {
        int dealerScore = participants.getDealer().calculateScore();
        int playerScore = player.calculateScore();

        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerScore < playerScore && player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (dealerScore < playerScore) {
            return GameResult.WIN;
        }
        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }
        return compareSameScore(player);
    }

    private GameResult compareSameScore(Participant player) {
        Participant dealer = participants.getDealer();
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public Map<Participant, Double> calculateRewards(Map<Participant, GameResult> gameResult, Dealer dealer) {
        Map<Participant, Double> rewards = new LinkedHashMap<>();

        for (Entry<Participant, GameResult> userGameResult : gameResult.entrySet()) {
            Participant participant = userGameResult.getKey();
            GameResult result = userGameResult.getValue();
            rewards.put(participant, result.calculateReward(participant, dealer));
        }

        rewards.put(dealer, dealer.getBetMoney());
        return rewards;
    }


}
