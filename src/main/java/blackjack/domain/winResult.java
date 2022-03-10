package blackjack.domain;

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
        calculateResult(dealer, players);
    }

    private void initDealerResult() {
        Arrays.stream(Judgement.values())
                .forEach(value -> dealerResult.put(value, 0));
    }

    private void calculateResult(Dealer dealer, List<Player> players) {
        players.forEach(player -> judge(dealer, player));
    }

    private void judge(Dealer dealer, Player player) {
        int dealerScore = Rule.INSTANCE.calculateSum(dealer.getCards());
        int playerScore = Rule.INSTANCE.calculateSum(player.getCards());

        if (Rule.INSTANCE.isBust(player.getCards())) {
            dealerResult.merge(Judgement.WIN, 1, Integer::sum);
            playersResult.put(player.getName(), Judgement.LOSE);
            return;
        }

        if (Rule.INSTANCE.isBust(dealer.getCards())) {
            dealerResult.merge(Judgement.LOSE, 1, Integer::sum);
            playersResult.put(player.getName(), Judgement.WIN);
            return;
        }

        if (dealerScore == playerScore) {
            dealerResult.merge(Judgement.DRAW, 1, Integer::sum);
            playersResult.put(player.getName(), Judgement.DRAW);
            return;
        }
        if (dealerScore > playerScore) {
            dealerResult.merge(Judgement.WIN, 1, Integer::sum);
            playersResult.put(player.getName(), Judgement.LOSE);
            return;
        }
        dealerResult.merge(Judgement.LOSE, 1, Integer::sum);
        playersResult.put(player.getName(), Judgement.WIN);
    }

    public Map<Judgement, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, Judgement> getPlayersResult() {
        return playersResult;
    }
}
