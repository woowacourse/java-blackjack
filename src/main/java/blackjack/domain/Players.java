package blackjack.domain;

import blackjack.domain.participant.Player;
import blackjack.dto.PlayerResult;
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
    
    public List<PlayerResult> getWinningResults(int dealerScore) {
        List<PlayerResult> playerResults = new ArrayList<>();
        
        for (Player player : players) {
            int playerScore = player.getTotalScoreForResult();
            GameResult gameResult = getGameResult(dealerScore, playerScore);
            PlayerResult playerResult = new PlayerResult(player.getNickname(), gameResult);
            playerResults.add(playerResult);
        }
        
        return playerResults;
    }
    
    private GameResult getGameResult(int dealerScore, int playerScore) {
        if (dealerScore < playerScore) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }
}
