package blackjack.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Players {

    private static final int CAPACITY = 8;
    private final List<Player> players;

    public Players(List<Player> players) {
        validateCapacity(players);
        this.players = new ArrayList<>(players);
    }

    public void drawAll(Drawable drawable) {
        for (Player player : players) {
            player.drawCard(drawable);
        }
    }

    private void validateCapacity(List<Player> players) {
        if (players.size() > CAPACITY) {
            throw new IllegalArgumentException("인원수는 8명을 넘을 수 없습니다.");
        }
    }

    public List<Player> getValue() {
        return List.copyOf(players);
    }

    public ScoreResult compete(Dealer dealer) {
        Map<Score, Integer> dealerResult = new EnumMap<>(Score.class);
        for (Score value : Score.values()) {
            dealerResult.put(value, 0);
        }
        Map<String, Score> playerResults = new HashMap<>();

        for (Player player : players) {
            Score score = player.compete(dealer);
            playerResults.put(player.getName(), score);
            dealerResult.merge(Score.inverse(score), 1, Integer::sum);
        }
        return new ScoreResult(dealerResult, playerResults);
    }
}
