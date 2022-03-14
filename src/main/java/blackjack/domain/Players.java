package blackjack.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Players {

    private static final int CAPACITY = 8;
    public static final int ONE_INDEX = 1;

    private final List<Player> players;
    private int currentTurnIndex;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
        this.currentTurnIndex = 0;
        validateCapacity(players);
    }

    private void validateCapacity(List<Player> players) {
        if (players.size() > CAPACITY) {
            throw new IllegalArgumentException("인원수는 8명을 넘을 수 없습니다.");
        }
    }

    public void drawAll(Drawable drawable) {
        for (Player player : players) {
            player.drawCard(drawable.draw());
        }
    }

    public ScoreResult compete(Player dealer) {
        Map<Score, Integer> dealerResult = new EnumMap<>(Score.class);
        initDealerResult(dealerResult);
        Map<String, Score> playerResults = new HashMap<>();

        for (Player player : players) {
            Score score = player.compete(dealer);
            playerResults.put(player.getName(), score);
            dealerResult.merge(Score.inverse(score), 1, Integer::sum);
        }
        return new ScoreResult(dealerResult, playerResults);
    }

    private void initDealerResult(Map<Score, Integer> dealerResult) {
        for (Score value : Score.values()) {
            dealerResult.put(value, 0);
        }
    }

    public void drawPlayerCard(Drawable drawable) {
        getCurrentTurnPlayer().drawCard(drawable.draw());
    }

    public boolean isBustCurrentTurnPlayer() {
        return getCurrentTurnPlayer().isBust();
    }

    public boolean hasNoNext() {
        return currentTurnIndex + ONE_INDEX >= players.size();
    }

    public void proceedTurn() {
        if (hasNoNext()) {
            throw new IllegalStateException("proceed 할 수 없습니다.");
        }
        currentTurnIndex++;
    }

    public Player getCurrentTurnPlayer() {
        return players.get(currentTurnIndex);
    }

    public List<Player> getValue() {
        return List.copyOf(players);
    }
}
