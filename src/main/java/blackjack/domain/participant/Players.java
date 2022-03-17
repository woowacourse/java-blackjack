package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.validation.PlayerNameValidator;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.MatchStatus;

public class Players {

    private final List<Player> players;

    private Players(final List<String> playerNames, final Deck deck) {
        PlayerNameValidator.validatePlayerName(playerNames);
        this.players = playerNames.stream()
                .map(playerName -> Player.readyToPlay(playerName, deck))
                .collect(Collectors.toUnmodifiableList());
    }

    public static Players readyToPlay(final List<String> names, final Deck deck) {
        return new Players(names, deck);
    }

    public void betAmount(final String playerName, final int amount) {
        final Player player = findByPlayerName(playerName);
        player.betAmount(amount);
    }

    public void drawCard(final String playerName, final Deck deck) {
        final Player player = findByPlayerName(playerName);
        player.drawCard(deck);
    }

    public boolean isPlayerPossibleToDrawCard(final String playerName) {
        final Player player = findByPlayerName(playerName);
        return player.isPossibleToDrawCard();
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
        final String playerName = player.getName();
        final MatchStatus matchStatus = dealer.judgeWinner(player);
        matchStatuses.put(playerName, matchStatus);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Card> getPlayerCards(final String playerName) {
        final Player player = findByPlayerName(playerName);
        return player.getCards();
    }

    private Player findByPlayerName(final String playerName) {
        return players.stream()
                .filter(player -> player.equalsName(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니다."));
    }

}
