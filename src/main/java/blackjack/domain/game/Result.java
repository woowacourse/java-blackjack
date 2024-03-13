package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final Map<Player, PlayerState> resultMap;

    private Result(Map<Player, PlayerState> resultMap) {
        this.resultMap = resultMap;
    }

    public static Result of(List<Player> players, Dealer dealer) {
        Map<Player, PlayerState> currentResultMap = new LinkedHashMap<>();

        for (Player player : players) {
            PlayerState playerState = decidePlayerState(player, dealer);
            currentResultMap.put(player, playerState);
        }

        return new Result(currentResultMap);
    }

    private static PlayerState decidePlayerState(Player player, Dealer dealer) {
        if (player.isBlackjack()) {
            return decidePlayerStateByBlackjack(dealer);
        }
        if (player.isBusted()) {
            return PlayerState.LOSE;
        }
        if (dealer.isBusted()) {
            return PlayerState.WIN;
        }
        return decidePlayerStateByScore(player, dealer);
    }

    private static PlayerState decidePlayerStateByBlackjack(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return PlayerState.TIE;
        }
        return PlayerState.BLACKJACK;
    }

    private static PlayerState decidePlayerStateByScore(Player player, Dealer dealer) {
        if (dealer.calculateScore() < player.calculateScore()) {
            return PlayerState.WIN;
        }
        if (dealer.calculateScore() > player.calculateScore()) {
            return PlayerState.LOSE;
        }
        return PlayerState.TIE;
    }

    public long countDealerWins() {
        return resultMap.values().stream()
                .filter(value -> value == PlayerState.LOSE)
                .count();
    }

    public long countDealerLoses() {
        return resultMap.values().stream()
                .filter(value -> value == PlayerState.WIN || value == PlayerState.BLACKJACK)
                .count();
    }

    public long countDealerTies() {
        return resultMap.values().stream()
                .filter(value -> value == PlayerState.TIE)
                .count();
    }

    public PlayerState getPlayerState(Player player) {
        return resultMap.get(player);
    }
}
