package domain.participant;

import domain.MatchResult;
import domain.card.Card;

import java.util.*;

public class Players {

    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public Players(List<Player> players) {
        validateSize(players);
        validateDuplicateName(players);
        this.players = players;
    }

    public Player findBy(Player targetPlayer) {
        for (Player player : players) {
            if (player.equals(targetPlayer)) {
                return player;
            }
        }

        throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
    }

    public Map<Player, MatchResult> calculateMatchResult(Dealer dealer) {
        Map<Player, MatchResult> result = new HashMap<>();

        for (Player player : players) {
            result.put(player, MatchResult.determineMatchResultWithDealer(player, dealer));
        }

        return result;
    }

    public Map<Player, Integer> calculateProfitResult(Map<Player, MatchResult> playersMatchResult) {
        Map<Player, Integer> profitResult = new HashMap<>();

        for (Map.Entry<Player, MatchResult> matchResultEntry : playersMatchResult.entrySet()) {
            Player player = matchResultEntry.getKey();
            MatchResult matchResult = matchResultEntry.getValue();

            profitResult.put(player, player.applyMatchResultToBet(matchResult));
        }

        return profitResult;
    }

    public Map<Player, List<Card>> getPlayersHand() {
        Map<Player, List<Card>> playersHand = new HashMap<>();

        for (Player player : players) {
            playersHand.put(player, player.getCards());
        }

        return playersHand;
    }

    public Map<Player, Integer> getPlayersScore() {
        Map<Player, Integer> playersScore = new HashMap<>();

        for (Player player : players) {
            playersScore.put(player, player.getScore());
        }

        return playersScore;
    }

    private void validateDuplicateName(List<Player> playerNames) {
        Set<Player> uniqueNames = new HashSet<>(playerNames);

        if (uniqueNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private void validateSize(List<Player> playerNames) {
        if (playerNames.size() > MAX_PLAYER_SIZE)
            throw new IllegalArgumentException("플레이어 인원 수는 5명 이하여야 합니다.");
    }

    public List<Player> getPlayers() {
        return players;
    }
}
