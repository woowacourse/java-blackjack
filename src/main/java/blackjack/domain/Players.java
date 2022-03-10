package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Map<Player, Map<Score, Integer>> compete(Dealer dealer) {
        Map<Player, Map<Score, Integer>> scoreStructure = initScoreStructure(dealer);

        for (Player player : players) {
            Score score = player.compete(dealer);
            scoreStructure.get(player).merge(score, 1, Integer::sum);
            scoreStructure.get(dealer).merge(Score.inverse(score), 1, Integer::sum);
        }
        return scoreStructure;
    }

    private Map<Player, Map<Score, Integer>> initScoreStructure(Dealer dealer) {
        Map<Player, Map<Score, Integer>> map = new HashMap<>();

        List<Player> players = new ArrayList<>(this.players);
        players.add(dealer);

        for (Player player : players) {
            map.put(player, new EnumMap<Score, Integer>(Score.class));
            Arrays.stream(Score.values())
                .forEach(score -> map.get(player).put(score, 0));
        }
        return map;
    }
}
