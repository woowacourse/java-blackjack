package domain.participant;

import java.util.List;

public class Players {
    private final List<Player> players;

    public static Players from(List<String> names){
        validatePlayerNumbers(names);
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    private Players(List<Player> players) {
        this.players = players;
    }

    public void hitCards(Dealer dealer) {
        players.forEach(player -> player.hitCards(dealer));
    }

    public List<Player> getPlayers() {
        return players;
    }

    private static void validatePlayerNumbers(List<String> names) {
        if (names.isEmpty() || names.size() > 6) {
            throw new IllegalArgumentException("[ERROR] 플레이어 인원은 1~6명 입니다.");
        }
    }
}
