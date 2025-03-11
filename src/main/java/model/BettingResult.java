package model;

import java.util.Map;
import model.participant.Player;
import model.participant.Players;

public class BettingResult {
    private final Map<Player, Integer> betting;

    public BettingResult(Map<Player, Integer> betting) {
        this.betting = betting;
    }

    public Integer getBettingNet(Player player) {
        return betting.get(player);
    }

    public void calculatePlayerBettingResult(Players players, ParticipantWinningResult participantWinningResult) {
        for (Player player : players.getPlayers()){
            computeResultByWinningStatus(player, participantWinningResult.getPlayerGameResult(player));
        }
    }

    private void computeResultByWinningStatus(Player player, GameResult gameResult) {
        int bettingPrice = betting.get(player);
        if (gameResult == GameResult.WIN){
            betting.put(player, bettingPrice * 2);
        }
        if (gameResult == GameResult.LOSE){
            betting.put(player, 0);
        }
        if (gameResult == GameResult.BLACKJACK){
            betting.put(player, bettingPrice + bettingPrice * 3/2);
        }
    }
}

