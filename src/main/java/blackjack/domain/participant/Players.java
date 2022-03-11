package blackjack.domain.participant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.WinningResult;

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
            final String playerName = player.getParticipantName();
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

    public void play(final Deck deck, final CardDrawCallback callback) {
        for (Player player : players) {
            player.continueDraw(deck, callback);
        }
    }

}
