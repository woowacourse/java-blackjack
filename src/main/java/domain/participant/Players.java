package domain.participant;

import domain.MatchResult;
import domain.card.Deck;

import java.util.*;

public class Players {

    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validateSize(playerNames);
        validateDuplicateName(playerNames);
        this.players = from(playerNames);
    }

    public void initialHands(Deck deck, int initialCardCount) {
        for (Player player : players) {
            for (int i = 0; i < initialCardCount; i++) {
                player.receive(deck.drawCard());
            }
        }
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

    private List<Player> from(List<String> playerNames) {
        return playerNames.stream()
                .map(String::trim)
                .map(Player::new)
                .toList();
    }

    private void validateDuplicateName(List<String> playerNames) {
        Set<String> uniqueNames = new HashSet<>(playerNames);

        if (uniqueNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private void validateSize(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_SIZE)
            throw new IllegalArgumentException("플레이어 인원 수는 5명 이하여야 합니다.");
    }

    public List<Player> getPlayers() {
        return players;
    }
}
