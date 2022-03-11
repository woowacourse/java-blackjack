package blackjack.domain.game;

import blackjack.domain.participant.Participant;

import java.util.*;

public class GameResult {

    private final Map<Participant, MatchResult> gameResult = new LinkedHashMap<>();

    public GameResult(List<Participant> players, Participant dealer) {
        initialGameResult(new ArrayList<>(players), dealer);
    }

    private void initialGameResult(List<Participant> players, Participant dealer) {
        for (Participant player : players) {
            if (playerWinCondition(player, dealer)) {
                gameResult.put(player, MatchResult.WIN);
                continue;
            }
            gameResult.put(player, MatchResult.LOSE);
        }
    }

    private boolean playerWinCondition(Participant player, Participant dealer) {
        return !player.getCards().isBust()
                && (dealer.getCards().isBust() || dealer.getCards().sum() < player.getCards().sum());
    }

    public MatchResult getMatchResult(Participant player) {
        return gameResult.get(player);
    }

    public long getDealerWinCount() {
        return gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == MatchResult.LOSE)
                .count();
    }

    public long getDealerLoseCount() {
        return gameResult.size() - getDealerWinCount();
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
