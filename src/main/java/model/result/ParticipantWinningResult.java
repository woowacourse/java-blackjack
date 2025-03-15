package model.result;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public final class ParticipantWinningResult {
    private final Map<Player, GameResult> result;

    public static ParticipantWinningResult of(final Players players, final Dealer dealer) {
        Map<Player, GameResult> result = new HashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player, checkPlayerWin(dealer, player));
        }
        return new ParticipantWinningResult(result);
    }

    public ParticipantWinningResult(final Map<Player, GameResult> result) {
        this.result = result;
    }

    public Map<GameResult, Integer> decideDealerWinning() {
        Map<GameResult, Integer> dealerResult = new HashMap<>();
        for (Player player : result.keySet()){
            GameResult playerGameResult = result.get(player);
            updateDealerResult(playerGameResult, dealerResult);
        }
        return dealerResult;
    }

    private static GameResult checkPlayerWin(final Dealer dealer, final Player player) {
        if (player.isBurst()) {
            return GameResult.LOSE;
        }
        if (dealer.isBurst()) {
            return GameResult.WIN;
        }
        return checkResultIfNotBurst(dealer, player);
    }

    private static GameResult checkResultIfNotBurst(final Dealer dealer, final Player player) {
        int dealerScore = dealer.calculateFinalScore();
        int playerScore = player.calculateFinalScore();

        if (dealerScore > playerScore){
            return GameResult.LOSE;
        }
        if (dealerScore < playerScore){
            return GameResult.WIN;
        }
        if (player.isBlackjack() && dealer.isBlackJack()) { //TODO 여기있어도 되는 거 맞나?
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private void updateDealerResult(final GameResult gameResult, final Map<GameResult, Integer> dealerResult) {
        dealerResult.merge(gameResult.findReverse(), 1, Integer::sum);
    }

    public GameResult findResultByPlayer(Player player) {
        return result.get(player);
    }

    public Map<Player, GameResult> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
