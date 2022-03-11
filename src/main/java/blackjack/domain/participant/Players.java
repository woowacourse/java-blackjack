package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.WinningResult;

import java.util.*;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<String> playerNames) {
        validatePlayerNamesDuplicated(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validatePlayerNamesDuplicated(final List<String> playerNames) {
        final Set<String> validNames = new HashSet<>(playerNames);
        if (validNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어명은 중복될 수 없습니다.");
        }
    }

    public static Players startWithTwoCards(final List<String> names, final Deck deck) {
        Players players = new Players(names);
        players.distributeCards(deck);
        return players;
    }

    public MatchResult judgeWinners(Dealer dealer) {
        final Map<String, WinningResult> winningResults = new HashMap<>();
        for (final Player player : players) {
            final String playerName = player.getName();
            final WinningResult winningResult = dealer.judgeWinner(player);
            winningResults.put(playerName, winningResult);
        }
        return new MatchResult(winningResults);
    }

    private void distributeCards(Deck deck) {
        for (final Player player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
        }
    }

    public List<Player> getStatuses() {
        return List.copyOf(players);
    }

    public boolean canHit(final int turnIndex) {
        Player player = players.get(turnIndex);

        return !player.isBurst();
    }

    public boolean isStillInGame(final int turnIndex) {
        return turnIndex < players.size();
    }

    public Player getCurrentPlayer(final int index) {
        return players.get(index);
    }

    public void drawCard(final int turnIndex, final Deck deck) {
        players.get(turnIndex).drawCard(deck);
    }
}
