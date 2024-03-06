package model;

import java.util.LinkedHashMap;
import java.util.Map;
import model.player.Player;
import model.player.Players;

public class BlackJack {

    private final Players players;

    public BlackJack(Players players) {
        this.players = players;
    }

    public void offerCardToPlayers(int cardCount) {
        players.offerCardToPlayers(cardCount);
    }

    public Map<Player, GameResult> findResult() {
        Map<Player, GameResult> result = new LinkedHashMap<>();

        Map<Player, Integer> sumPlayers = players.sumCardNumbersWithoutDealer();

        Player dealer = players.getDealer();
        int dealerResultSum = dealer.sumCardNumbers();

        for (Player player : sumPlayers.keySet()) {
            Integer sum = sumPlayers.get(player);
            int participantDifference = 21 - sum;
            int dealerDifference = Math.abs(21 - dealerResultSum);

            if(sum > 21) {
                if(dealerResultSum > 21) {
                    result.put(player, GameResult.DRAW);
                    continue;
                }
                result.put(player, GameResult.LOSE);
                continue;
            }
            if(dealerResultSum > 21) {
                result.put(player, GameResult.WIN);
                continue;
            }
            result.put(player, findGameResult(participantDifference, dealerDifference));
        }

        return result;
    }

    private GameResult findGameResult(int participantDifference, int dealerDifference) {
        if(participantDifference > dealerDifference) {
            return GameResult.LOSE;
        }
        if(participantDifference < dealerDifference) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}
