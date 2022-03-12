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
        players.forEach(player -> judge(dealer, player));
    }

    private void judge(Dealer dealer, Player player) {
        if (player.isBust()) {
            updateResult(player, Judgement.LOSE);
            return;
        }
        if (dealer.isBust()) {
            updateResult(player, Judgement.WIN);
            return;
        }
        if (dealer.isBlackJack() || player.isBlackJack()) {
            judgeWithBlackJack(dealer, player);
            return;
        }
        judgeWithoutBust(dealer, player);
    }

    private void judgeWithBlackJack(Dealer dealer, Player player) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            updateResult(player, Judgement.DRAW);
            return;
        }
        if (dealer.isBlackJack()) {
            updateResult(player, Judgement.LOSE);
            return;
        }
        if (player.isBlackJack()) {
            updateResult(player, Judgement.WIN);
        }
    }

    private void judgeWithoutBust(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();
        if (dealerScore == playerScore) {
            updateResult(player, Judgement.DRAW);
            return;
        }
        if (dealerScore > playerScore) {
            updateResult(player, Judgement.LOSE);
            return;
        }
        updateResult(player, Judgement.WIN);
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
