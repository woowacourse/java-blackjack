package domain.participant;

import domain.MatchResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {
    private static final int MAXIMUM_PLAYER_NUMBER = 6;

    private final List<Player> players;

    public static Players from(final List<String> names) {
        validatePlayerNumbers(names);
        validateIsDuplicate(names);
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    private Players(final List<Player> players) {
        this.players = players;
    }

    public void hitCards(final Dealer dealer) {
        players.forEach(player -> player.hitCards(dealer));
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck, final Dealer dealer) {
        for (Player player : players) {
            player.draw(answer, playerDeck, dealer);
        }
    }

    public LinkedHashMap<Player, MatchResult> calculateWinner(final int dealerSum) {
        LinkedHashMap<Player, MatchResult> res = new LinkedHashMap<>();
        for (Player player : players) {
            res.put(player, player.calculateWinner(dealerSum));
        }
        return res;
    }

    private static void validatePlayerNumbers(final List<String> names) {
        if (names.isEmpty() || names.size() > MAXIMUM_PLAYER_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 플레이어 인원은 1~6명 입니다.");
        }
    }

    private static void validateIsDuplicate(final List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름의 플레이어가 게임에 참여할 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
