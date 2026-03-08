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

    public Map<String, MatchResult> calculateResult(Dealer dealer) {
        Map<String, MatchResult> playersResult = new HashMap<>();

        for (Player player : players) {
            if (isBustResult(dealer, player, playersResult)) continue;
            if (isHigherScoreThanDealer(dealer, player, playersResult)) continue;
            if (isDrawResult(dealer, player, playersResult)) continue;

            playersResult.put(player.getName(), MatchResult.LOSE);
        }

        return playersResult;
    }

    private boolean isBustResult(Dealer dealer, Player player, Map<String, MatchResult> playersResult) {
        if (player.isBust()) {
            playersResult.put(player.getName(), MatchResult.LOSE);
            return true;
        }

        if (dealer.isBust()) {
            playersResult.put(player.getName(), MatchResult.WIN);
            return true;
        }

        return false;
    }

    private boolean isHigherScoreThanDealer(Dealer dealer, Player player, Map<String, MatchResult> playersResult) {
        if (player.isHigherThan(dealer)) {
            playersResult.put(player.getName(), MatchResult.WIN);
            return true;
        }

        return false;
    }

    private boolean isDrawResult(Dealer dealer, Player player, Map<String, MatchResult> playersResult) {
        if (player.isTie(dealer)) {
            return isDrawWithBlackJack(dealer, player, playersResult);
        }

        return false;
    }

    private boolean isDrawWithBlackJack(Dealer dealer, Player player, Map<String, MatchResult> playersResult) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            playersResult.put(player.getName(), MatchResult.WIN);
            return true;
        }

        if (!player.isBlackJack() && dealer.isBlackJack()) {
            playersResult.put(player.getName(), MatchResult.LOSE);
            return true;
        }

        if (!player.isBlackJack() && !dealer.isBlackJack()) {
            playersResult.put(player.getName(), MatchResult.DRAW);
            return true;
        }

        return false;
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
