package blackjack.domain.player;

import java.util.*;

public class Players {

    private static final int MAX_PLAYER_SIZE = 7;
    public static final int MINIMUM_PLAYER_SIZE = 1;

    private final Queue<Player> players;

    public Players(List<Player> players) {
        validatePlayerSizeAndDuplication(players);
        this.players = new LinkedList<>(players);
    }

    public Player getCurrentTurn() {
        return players.peek();
    }

    public void passTurnToNext() {
        players.offer(players.poll());
    }

    public void passTurnUntilHitable() {
        int count = 0;
        while (count < 8 && !getCurrentTurn().isAbleToHit()) {
            passTurnToNext();
            count++;
        }
    }

    public boolean isPossibleToPlay() {
        boolean isPossibleToPlay = false;
        for (Player player : players) {
            isPossibleToPlay |= player.isAbleToHit();
        }
        return isPossibleToPlay;
    }

    public List<Player> toList() {
        return new ArrayList<>(players);
    }

    private void validatePlayerSizeAndDuplication(List<Player> players) {
        int inputPlayerSize = players.size();
        long distinctPlayerSize = players.stream().distinct().count();
        if ( distinctPlayerSize < MINIMUM_PLAYER_SIZE || distinctPlayerSize > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수는 " + MINIMUM_PLAYER_SIZE + " 이상, " +
                    MAX_PLAYER_SIZE + " 이하여야 합니다");
        }
        if (inputPlayerSize != distinctPlayerSize) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다");
        }
    }

    public Map<String, Outcome> calculateResult(Dealer dealer) {
        Map<String, Outcome> result = new HashMap<>();
        for (Player player : players) {
            Outcome outcome = player.compareScoreWith(dealer);
            result.put(player.getName(), outcome);
        }
        return result;
    }
}
