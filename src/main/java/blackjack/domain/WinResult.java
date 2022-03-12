package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinResult {

    private final Map<Judgement, Integer> dealerResult;
    private final Map<String, Judgement> playersResult;

    public WinResult(Dealer dealer, List<Player> players) {
        dealerResult = new EnumMap<>(Judgement.class);
        playersResult = new LinkedHashMap<>();

        initDealerResult();
        calculateResult(dealer, List.copyOf(players));
    }

    private void initDealerResult() {
        Arrays.stream(Judgement.values())
            .forEach(value -> dealerResult.put(value, 0));
    }

    private void calculateResult(Dealer dealer, List<Player> players) {
        players.forEach(player -> updateResult(player, judge(dealer, player)));
    }

    private Judgement judge(Dealer dealer, Player player) {
        if (player.isBust()) {
            return Judgement.LOSE;
        }
        if (dealer.isBust()) {
            return Judgement.WIN;
        }
        if (dealer.isBlackJack() || player.isBlackJack()) {
            return judgeWithBlackJack(dealer, player);
        }
        return judgeWithScore(dealer, player);
    }

    private Judgement judgeWithBlackJack(Dealer dealer, Player player) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return Judgement.DRAW;
        }
        if (dealer.isBlackJack()) {
            return Judgement.LOSE;
        }
        return Judgement.WIN;
    }

    private Judgement judgeWithScore(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();
        if (dealerScore == playerScore) {
            return Judgement.DRAW;
        }
        if (dealerScore > playerScore) {
            return Judgement.LOSE;

        }
        return Judgement.WIN;
    }

    private void updateResult(Player player, Judgement playerJudgement) {
        dealerResult.merge(playerJudgement.getOpposite(), 1, Integer::sum);
        playersResult.put(player.getName(), playerJudgement);
    }

    public Map<Judgement, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }

    public Map<String, Judgement> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }
}
