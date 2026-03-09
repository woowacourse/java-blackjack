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

    public Map<Player, MatchResult> calculateResult(Dealer dealer) {
        Map<Player, MatchResult> result = new HashMap<>();

        for (Player player : players) {
            result.put(player, determineMatchResult(player, dealer));
        }

        return result;
    }

    private MatchResult determineMatchResult(Player player, Dealer dealer) {
        if (player.isBust()) return MatchResult.LOSE;
        if (dealer.isBust()) return MatchResult.WIN;
        if (player.isHigherThan(dealer)) return MatchResult.WIN;

        if (player.isTie(dealer)) {
            if (player.isBlackJack() && !dealer.isBlackJack()) return MatchResult.WIN;
            if (!player.isBlackJack() && dealer.isBlackJack()) return MatchResult.LOSE;
            return MatchResult.DRAW;
        }

        return MatchResult.LOSE;
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
