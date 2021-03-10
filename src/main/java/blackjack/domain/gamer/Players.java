package blackjack.domain.gamer;

import blackjack.domain.MatchResult;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Players {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 8;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayerCount(players);
        this.players = new ArrayList<>(players);
    }

    private void validatePlayerCount(List<Player> player) {
        if (player.size() > MAX_COUNT || player.size() < MIN_COUNT) {
            throw new IllegalArgumentException("플레이어 인원 수는 1명 이상 8명 이하 입니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<Player, MatchResult> verifyResultByCompareScore(Dealer dealer) {
        Map<Player, MatchResult> result = new LinkedHashMap<>();
        for (Player player : players) {
            MatchResult matchResult = MatchResult
                    .matchPlayerAndDealer(player.getTakenCards().calculateScore(),
                                          dealer.getTakenCards().calculateScore());
            result.put(player, matchResult);
        }
        return result;
    }
}
