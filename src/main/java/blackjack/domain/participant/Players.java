package blackjack.domain.participant;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.MatchStatus;

public class Players {

    private final List<Player> players;

    private Players(final List<String> playerNames, final Deck deck) {
        validateNameNotDuplicated(playerNames);
        validateNameCountEnough(playerNames);
        this.players = playerNames.stream()
                .map(playerName -> Player.readyToPlay(playerName, deck))
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validateNameNotDuplicated(final List<String> playerNames) {
        if (isPlayerNameDuplicated(playerNames)) {
            throw new IllegalArgumentException("플레이어명은 중복될 수 없습니다.");
        }
    }

    private static void validateNameCountEnough(final List<String> playerNames) {
        if (isNameCountEnough(playerNames)) {
            throw new IllegalArgumentException("플레이어는 8명 이하여야 합니다.");
        }
    }

    private static boolean isNameCountEnough(final List<String> playerNames) {
        final int allowedMaximumNameCount = 8;
        return playerNames.size() > allowedMaximumNameCount;
    }

    private static boolean isPlayerNameDuplicated(final List<String> playerNames) {
        return playerNames.stream()
                .anyMatch(playerName -> Collections.frequency(playerNames, playerName) > 1);
    }

    public static Players readyToPlay(final List<String> names, final Deck deck) {
        return new Players(names, deck);
    }

    public void drawCardsPerPlayer(final Deck deck, final CardDrawCallback callback) {
        for (final Player player : players) {
            player.drawCards(deck, callback);
        }
    }

    public MatchResult judgeWinners(final Dealer dealer) {
        final Map<String, MatchStatus> matchStatuses = new LinkedHashMap<>();
        for (final Player player : players) {
            appendJudgedWinner(matchStatuses, player, dealer);
        }
        return new MatchResult(matchStatuses);
    }

    private void appendJudgedWinner(final Map<String, MatchStatus> matchStatuses,
                                    final Player player,
                                    final Dealer dealer) {
        final String playerName = player.getParticipantName();
        final MatchStatus matchStatus = dealer.judgeWinner(player);
        matchStatuses.put(playerName, matchStatus);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

}
