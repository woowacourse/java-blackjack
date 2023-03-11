package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardResponse;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.exception.PlayerNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    static Players from(final List<String> playerNames, final List<Integer> bettingMoneys) {
        validatePlayerNames(playerNames);
        validateSize(playerNames, bettingMoneys);
        final List<Player> players = createPlayers(playerNames, bettingMoneys);
        return new Players(players);
    }

    private static void validateSize(final List<String> playerNames, final List<Integer> bettingMoneys) {
        if (playerNames.size() != bettingMoneys.size()) {
            throw new IllegalArgumentException("플레이어 이름과 베팅 금액의 수가 일치하지 않습니다.");
        }
    }

    private static List<Player> createPlayers(final List<String> playerNames, final List<Integer> bettingMoneys) {
        final List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), bettingMoneys.get(i)));
        }
        return players;
    }

    private static void validatePlayerNames(final List<String> playerNames) {
        new Names(playerNames);
    }

    void distributeInitialCards(final Deck deck) {
        for (final Player player : players) {
            player.drawInitialCard(deck);
        }
    }

    List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    boolean isDrawable(final String playerName) {
        return players.stream()
                .filter(player -> player.hasName(playerName))
                .findFirst()
                .map(Player::isDrawable)
                .orElseThrow(PlayerNotFoundException::new);
    }

    void draw(final String playerName, final Card card) {
        final Player targetPlayer = players.stream()
                .filter(player -> player.hasName(playerName))
                .findFirst()
                .orElseThrow(PlayerNotFoundException::new);
        targetPlayer.drawCard(card);
    }

    List<Player> getPlayers() {
        return players;
    }

    List<CardResponse> getPlayerCards(final String playerName) {
        return players.stream()
                .filter(player -> player.hasName(playerName))
                .findFirst()
                .map(Player::getCards)
                .orElseThrow(PlayerNotFoundException::new);
    }

    Map<String, Integer> calculatePlayersScore() {
        final Map<String, Integer> playerScore = new LinkedHashMap<>();
        for (final Player player : players) {
            playerScore.put(player.getName(), player.currentScore());
        }
        return playerScore;
    }

    Map<String, List<CardResponse>> getPlayersCards() {
        final Map<String, List<CardResponse>> playerCards = new HashMap<>();
        for (final Player player : players) {
            playerCards.put(player.getName(),
                    player.getCards());
        }
        return playerCards;
    }
}
