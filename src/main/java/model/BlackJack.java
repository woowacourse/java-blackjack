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

    public void offerCardToPlayer(String name, int cardCount) {
        players.offerCardToPlayer(name, cardCount);
    }

    public Map<Player, GameResult> findResult() {
        Map<Player, Integer> sumPlayers = players.sumCardNumbersWithoutDealer();

        return sumPlayers.entrySet().stream()
                .collect(toMap(
                        Entry::getKey,
                        sumPlayer -> findGameResult(sumPlayer.getKey(), players.getDealer())
                ));
    }

    private GameResult findGameResult(Player participant, Player dealer) {
        if (participant.isOverMaximumSum() && dealer.isOverMaximumSum()) {
            return GameResult.DRAW;
        }
        if (participant.isOverMaximumSum()) {
            return GameResult.LOSE;
        }
        if (dealer.isOverMaximumSum()) {
            return GameResult.WIN;
        }
        return findResultByMinimumDifference(participant.findPlayerDifference(), dealer.findPlayerDifference());
    }

    private GameResult findResultByMinimumDifference(int participantDifference, int dealerDifference) {
        if (participantDifference > dealerDifference) {
            return GameResult.LOSE;
        }
        if (participantDifference < dealerDifference) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}
