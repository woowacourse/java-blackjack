package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> playerNames) {
        validatePlayerNames(playerNames);
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validatePlayerNames(List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.isYourName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 가진 플레이어를 찾을 수 없습니다."));
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Card> getPlayerCards(String playerName) {
        Player player = findPlayerByName(playerName);
        return player.getCards();
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public void handInitialCards(Deck deck) {
        for (Player player : players) {
            handInitialCards(player, deck);
        }
    }

    private void handInitialCards(Player player, Deck deck) {
        for (int i = 0; i < 2; i++) {
            player.take(deck.draw());
        }
    }

    public PlayerWinResults computePlayerWinResults(Dealer dealer) {
        PlayerWinResults playerWinResults = new PlayerWinResults();
        for (Player player : players) {
            playerWinResults.addResultByPlayerName(player.getName(), dealer.judge(player));
        }
        return playerWinResults;
    }

    public List<String> findCanDrawPlayerNames() {
        List<String> canDrawPlayerNames = new ArrayList<>();
        for (Player player : players) {
            addCanDrawPlayer(player, canDrawPlayerNames);
        }
        return canDrawPlayerNames;
    }

    private void addCanDrawPlayer(Player player, List<String> canDrawPlayerNames) {
        if (player.canDraw()) {
            canDrawPlayerNames.add(player.getName());
        }
    }
}
