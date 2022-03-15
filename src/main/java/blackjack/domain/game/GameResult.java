package blackjack.domain.game;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Player, MatchResult> gameResult = new LinkedHashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        initGameResult(new ArrayList<>(players), dealer);
    }

    private void initGameResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            gameResult.put(player, playResult(player, dealer));
        }
    }

    private MatchResult playResult(Player player, Dealer dealer) {
        Cards playerCards = player.getCards();
        Cards dealerCards = dealer.getCards();

        if (isFirstCardsLose(playerCards, dealerCards)) {
            return MatchResult.LOSE;
        }

        if (isFirstCardsLose(dealerCards, playerCards)) {
            return MatchResult.WIN;
        }

        return MatchResult.PUSH;
    }

    private boolean isFirstCardsLose(Cards cards1, Cards cards2) {
        return cards1.isBust() || (!cards2.isBust() && cards1.sum() < cards2.sum());
    }

    public MatchResult getMatchResult(Player player) {
        return gameResult.get(player);
    }

    public long calculateDealerMatchResultCount(MatchResult matchResult) {
        long matchCount = getMatchResultCount(matchResult);

        if (matchResult == MatchResult.PUSH) {
            return matchCount;
        }
        return gameResult.size() - matchCount;
    }

    private long getMatchResultCount(MatchResult matchResult) {
        return gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == matchResult)
                .count();
    }

    public Map<Player, MatchResult> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "gameResult=" + gameResult +
                '}';
    }
}
