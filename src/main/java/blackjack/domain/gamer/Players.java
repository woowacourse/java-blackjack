package blackjack.domain.gamer;

import blackjack.domain.MatchResult;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Players {
    private static final int MAX_COUNT = 8;
    private final List<Player> players;

    public Players(String input) {
        List<Player> players = splitInput(input);
        validatePlayerCount(players);
        this.players = new ArrayList<>(players);
    }

    private void validatePlayerCount(List<Player> player) {
        if (player.size() > MAX_COUNT) {
            throw new IllegalArgumentException("최대 플레이어 인원 수는 8명입니다.");
        }
    }

    private List<Player> splitInput(String players) {
        return Arrays.stream(players.split(","))
                .map(s -> s.replaceAll(" ", ""))
                .map(Player::new)
                .collect(toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<Player, MatchResult> verifyResultByCompareScore(Dealer dealer) {
        Map<Player, MatchResult> result = new HashMap<>();
        //result.put(new Player("air"), MatchResult.WIN);
        for (Player player : players) {
            result.put(player, dealer.matchGame(player));
        }
        return result;
    }
}
