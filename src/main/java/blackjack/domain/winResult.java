package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class winResult {

    private final Map<Judgement, Integer> dealerResult;
    private final Map<String, Judgement> playersResult;

    public winResult(Dealer dealer, List<Player> players) {
        dealerResult = new EnumMap<>(Judgement.class);
        playersResult = new HashMap<>();

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
        if (Rule.INSTANCE.isBust(player.getCards())) {
            updateResult(player, Judgement.LOSE);
            return;
        }
        if (Rule.INSTANCE.isBust(dealer.getCards())) {
            updateResult(player, Judgement.WIN);
            return;
        }
        judgeWithoutBust(player, Rule.INSTANCE.calculateSum(dealer.getCards()),
                Rule.INSTANCE.calculateSum(player.getCards()));
    }

    private void judgeWithoutBust(Player player, int dealerScore, int playerScore) {
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
        return Map.copyOf(dealerResult);
    }

    public Map<String, Judgement> getPlayersResult() {
        return Map.copyOf(playersResult);
    }
}
