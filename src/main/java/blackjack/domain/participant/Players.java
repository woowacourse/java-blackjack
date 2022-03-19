package blackjack.domain.participant;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.condition.PlayerCount;
import blackjack.domain.participant.state.State;
import blackjack.domain.result.MatchCalculator;
import blackjack.domain.result.MatchStatus;

public class Players {

    private final List<Player> players;

    private Players(final List<String> playerNames, final Deck deck) {
        validateNameNotDuplicated(playerNames);
        validateNameCountIsEnough(playerNames);
        this.players = playerNames.stream()
                .map(playerName -> Player.readyToPlay(playerName, deck.distributeInitialCards()))
                .collect(Collectors.toUnmodifiableList());
    }

    public static Players readyToPlay(final List<String> names, final Deck deck) {
        return new Players(names, deck);
    }

    public Player findByPlayerName(final String playerName) {
        return players.stream()
                .filter(player -> player.equalsName(playerName))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("플레이어를 찾을 수 없습니다."));
    }

    public Map<Player, MatchStatus> judgeMatchStatusOfPlayers(final Dealer dealer) {
        final Map<Player, MatchStatus> matchStatuses = new LinkedHashMap<>();
        for (final Player player : players) {
            final MatchStatus matchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
            matchStatuses.put(player, matchStatus);
        }
        return matchStatuses;
    }

    public boolean isAnyPlayerNotFinished() {
        return players.stream()
                .map(Participant::getState)
                .anyMatch(this::isNotFinished);
    }

    private boolean isNotFinished(final State playerState) {
        return !playerState.isFinished();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<Card> getPlayerCards(final String playerName) {
        final Player player = findByPlayerName(playerName);
        return player.getCards();
    }

    private static void validateNameNotDuplicated(final List<String> playerNames) {
        if (isPlayerNameDuplicated(playerNames)) {
            throw new IllegalArgumentException("플레이어명은 중복될 수 없습니다.");
        }
    }

    private static boolean isPlayerNameDuplicated(final List<String> playerNames) {
        return playerNames.stream()
                .anyMatch(playerName -> Collections.frequency(playerNames, playerName) > 1);
    }

    private static void validateNameCountIsEnough(final List<String> playerNames) {
        if (PlayerCount.isCountOutOfRange(playerNames.size())) {
            throw new IllegalArgumentException("플레이어는 8명 이하여야 합니다.");
        }
    }

}
