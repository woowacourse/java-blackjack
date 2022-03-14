package domain.game;

import domain.card.Cards;
import domain.participant.Participant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Participant, MatchResult> gameResult = new LinkedHashMap<>();

    public GameResult(List<Participant> players, Participant dealer) {
        initialGameResult(new ArrayList<>(players), dealer);
    }

    private void initialGameResult(List<Participant> players, Participant dealer) {
        for (Participant player : players) {
            gameResult.put(player, playResult(player, dealer));
        }
    }

    private MatchResult playResult(Participant player, Participant dealer) {
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

    public MatchResult getMatchResult(Participant player) {
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

    public Map<Participant, MatchResult> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "gameResult=" + gameResult +
                '}';
    }
}
