package domain.participant;

import domain.MatchResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {

    private final List<Player> players;

    public static Players from(List<String> names){
        validatePlayerNumbers(names);
        validateIsDuplicate(names);
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    private static void validateIsDuplicate(List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름의 플레이어가 게임에 참여할 수 없습니다.");
        }
    }

    private Players(List<Player> players) {
        this.players = players;
    }

    public void hitCards(Dealer dealer) {
        players.forEach(player -> player.hitCards(dealer));
    }

    public void draw(Function<Player, Boolean> answer, Consumer<Player> playerDeck, Dealer dealer) {
        for (Player player : players) {
            player.draw(answer, playerDeck, dealer);
        }
    }

    private static void validatePlayerNumbers(List<String> names) {
        if (names.isEmpty() || names.size() > 6) {
            throw new IllegalArgumentException("[ERROR] 플레이어 인원은 1~6명 입니다.");
        }
    }

    public LinkedHashMap<Player, MatchResult> calculateWinner(int dealerSum) {
        LinkedHashMap<Player, MatchResult> res = new LinkedHashMap<>();
        for (Player player : players) {
            res.put(player, player.calculateWinner(dealerSum));
        }
        return res;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
