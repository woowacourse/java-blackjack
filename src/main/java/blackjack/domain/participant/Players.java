package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.State;
import blackjack.domain.participant.validation.PlayerNameValidator;
import blackjack.domain.result.MatchCalculator;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.MatchStatus;

public class Players {

    private final List<Player> players;

    private Players(final List<String> playerNames, final Deck deck) {
        validatePlayerNames(playerNames);
        this.players = playerNames.stream()
                .map(playerName -> Player.readyToPlay(playerName, deck.distributeInitialCards()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void validatePlayerNames(final List<String> playerNames) {
        PlayerNameValidator.validateNameNotDuplicated(playerNames);
        PlayerNameValidator.validateNameCountEnough(playerNames);
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

    public MatchResult judgeMatchStatusOfPlayers(final Dealer dealer) {
        final Map<Player, MatchStatus> matchStatuses = new LinkedHashMap<>();
        for (final Player player : players) {
            final MatchStatus matchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
            matchStatuses.put(player, matchStatus);
        }
        return new MatchResult(matchStatuses);
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

}
