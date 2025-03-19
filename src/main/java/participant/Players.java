package participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    public static final int MAX_PLAYER_RANGE = 6;

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public void enterPlayer(Player player) {
        validateMaxPlayerRange();
        players.add(player);
    }

    private void validateMaxPlayerRange() {
        if (players.size() == MAX_PLAYER_RANGE) {
            throw new IllegalArgumentException(String.format("[ERROR] 게임에 참가할 수 있는 플레이어는 최대 %d명 입니다.", MAX_PLAYER_RANGE));
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
