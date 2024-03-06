package model;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Map.Entry;
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
        Map<Player, Integer> sumPlayers = players.sumCardNumbersWithoutDealer();
        Player dealer = players.getDealer();
        int dealerResultSum = dealer.sumCardNumbers();

        return sumPlayers.entrySet().stream()
                .collect(toMap(
                        Entry::getKey,
                        sumPlayer -> findGameResult(dealerResultSum, sumPlayer.getValue())
                ));
    }

    private GameResult findGameResult(int dealerResultSum, int participantSum) {
        int dealerDifference = Math.abs(21 - dealerResultSum);
        int participantDifference = 21 - participantSum;

        if (participantSum > 21 && dealerResultSum > 21) {
            return GameResult.DRAW;
        }
        if (participantSum > 21) {
            return GameResult.LOSE;
        }
        if(dealerResultSum > 21) {
            return GameResult.WIN;
        }

        return findResultByMinimumDifference(participantDifference, dealerDifference);
    }

    private GameResult findResultByMinimumDifference(int participantDifference, int dealerDifference) {
        if(participantDifference > dealerDifference) {
            return GameResult.LOSE;
        }
        if(participantDifference < dealerDifference) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}
