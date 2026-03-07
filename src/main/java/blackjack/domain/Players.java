package blackjack.domain;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Role;
import blackjack.dto.WinningResult;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public static Players makePlayers(List<String> names) {
        List<Player> result = new ArrayList<>();
        for (String name : names) {
            validate(name);
            result.add(new Player(name, Role.PLAYER));
        }
        return from(result);
    }

    private static void validate(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
    }

    public List<String> getAllPlayerNickname() {
        List<String> result = new ArrayList<>();
        for (Player player : players) {
            result.add(player.getNickname());
        }
        return result;
    }

    public List<Player> getAllPlayers() {
        return players;
    }

    public String findDrawablePlayerNickname() {
        Player player = findDrawablePlayer();
        if (player == null) {
            return null;
        }
        return player.getNickname();
    }

    public PlayingCards addCardToAvailablePlayer(PlayingCards card) throws IllegalArgumentException {
        Player player = findDrawablePlayer();
        return player.receiveCard(card);
    }

    public Player findDrawablePlayer() {
        return players.stream()
            .filter(Player::isDrawable)
            .findFirst()
            .orElse(null);
    }

    public void dontWandDraw() {
        Player player = findDrawablePlayer();
        player.stop();
    }

    public List<WinningResult> getWinningResults(int dealerScore) {
        int dealerWin = 0;
        int dealerLost = 0;
        List<WinningResult> winningResults = getPlayerWinningResults(dealerScore);
        for (WinningResult winningResult : winningResults) {
            dealerWin += winningResult.getLoseCountForResult();
            dealerLost += winningResult.getWinCountForResult();
        }
        WinningResult dealerResult = new WinningResult("딜러", dealerWin, dealerLost);
        winningResults.addFirst(dealerResult);
        return winningResults;
    }

    public List<WinningResult> getPlayerWinningResults(int dealerScore) {
        List<WinningResult> winningResults = new ArrayList<>();
        for (Player player : players) {
            int playerScore = player.getTotalScoreForResult();
            int win = Integer.compare(playerScore, dealerScore);
            int lose = Integer.compare(dealerScore, playerScore);
            winningResults.add(new WinningResult(player.getNickname(), win, lose));
        }
        return winningResults;
    }
}
