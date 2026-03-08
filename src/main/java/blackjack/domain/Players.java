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
        List<Player> result = new ArrayList<>();
        for (String name : names) {
            validate(name);
            result.add(new Player(name));
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
