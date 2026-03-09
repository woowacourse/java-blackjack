package blackjack.domain;

import blackjack.domain.participant.Player;
import blackjack.dto.PlayerResult;
import blackjack.dto.WinningResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    
    private final List<Player> players;
    
    private Players(List<Player> players) {
        this.players = players;
    }
    
    public static Players from(List<Player> players) {
        return new Players(players);
    }
    
    public static Players makePlayers(List<String> names) {
        validate(names);
        
        List<Player> result = names.stream()
                .map(Player::new)
                .toList();
        
        return from(result);
    }
    
    private static void validate(List<String> names) {
        long distinct = names.stream().distinct().count();
        if (distinct != names.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }
    
    public List<Player> getAllPlayers() {
        return List.copyOf(players);
    }
    
    public String findDrawablePlayerNickname() {
        Player player = findDrawablePlayer();
        if (player == null) {
            return null;
        }
        return player.getNickname();
    }
    
    public String addCardToAvailablePlayer(List<Card> card) {
        Player player = findDrawablePlayer();
        return player.receiveCard(card);
    }
    
    public Player findDrawablePlayer() {
        return players.stream()
                .filter(Player::isDrawable)
                .findFirst()
                .orElse(null);
    }
    
    public void dontWantDraw() {
        Player player = findDrawablePlayer();
        player.stop();
    }

    public String getPlayersInfo() {
        return players
                .stream()
                .map(Player::getInfoSnapshot)
                .collect(Collectors.joining("\n"));
    }

    public String getAllPlayerNicknames() {
        return players
                .stream()
                .map(Player::getNickname)
                .collect(Collectors.joining(", "));
    }

    public String getPlayersResult() {
        return players
                .stream()
                .map(Player::getResultSnapshot)
                .collect(Collectors.joining("\n"));
    }

    public WinningResult getWinningResult(int dealerScore) {
        List<PlayerResult> playerResult = players
                .stream()
                .map(player -> player.determinePlayerResult(dealerScore))
                .toList();
        return getWinningResultWithDealerWinning(playerResult);
    }

    private WinningResult getWinningResultWithDealerWinning(List<PlayerResult> playerResult) {
        int dealerLoss = (int) playerResult.stream()
                .filter(result -> result.gameResult() == GameResult.WIN)
                .count();
        int dealerWin = playerResult.size() - dealerLoss;
        return new WinningResult(dealerLoss, dealerWin, playerResult);
    }
}
