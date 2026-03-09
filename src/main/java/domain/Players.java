package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private static final String DEALER_NAME = "딜러";
    private static final int BUST_THRESHOLD = 21;

    private final List<Player> playerList;

    public Players(List<String> names) {
        validate(names);
        playerList = new ArrayList<>();
        playerList.add(new Player(DEALER_NAME));
        for (String name : names) {
            playerList.add(new Player(name));
        }
    }

    private void validate(List<String> names) {
        for (String name : names) {
            if (name.isBlank()) {
                throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
            }
        }
    }

    public Player getDealer() {
        return playerList.get(0);
    }

    public List<Player> getGamePlayers() {
        return playerList.subList(1, playerList.size());
    }

    public Map<Player, Result> judge() {
        int dealerScore = getDealer().calculateScore();
        boolean dealerBust = dealerScore > BUST_THRESHOLD;
        Map<Player, Result> results = new LinkedHashMap<>();
        for (Player player : getGamePlayers()) {
            results.put(player, judgePlayer(player.calculateScore(), dealerScore, dealerBust));
        }
        return results;
    }

    private Result judgePlayer(int playerScore, int dealerScore, boolean dealerBust) {
        if (playerScore > BUST_THRESHOLD) {
            return Result.LOSE;
        }
        if (dealerBust) {
            return Result.WIN;
        }
        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public int getSize() {
        return playerList.size();
    }
}
