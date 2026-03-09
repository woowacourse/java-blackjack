package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantStatus;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.TotalWinningResult;
import java.util.List;

public class Players {
    
    private final List<Player> playerList;
    
    private Players(List<Player> playerList) {
        this.playerList = playerList;
    }
    
    public static Players from(List<Player> players) {
        return new Players(players);
    }
    
    public static Players makePlayers(List<String> names) {
        List<Player> result = names.stream()
                .map(Player::new)
                .toList();
        
        return from(result);
    }
    
    public void distributeCards(Deck deck) {
        playerList.forEach(player -> player.distributeCards(deck));
    }
    
    public List<ParticipantStatus> getPlayerCardStatus() {
        return playerList
                .stream()
                .map(Player::getCardStatus)
                .toList();
    }
    
    public ParticipantStatus giveCard(List<Card> card) {
        if (isDrawablePlayer()) {
            Player player = findDrawablePlayer();
            return player.receiveCard(card);
        }
        return null;
    }
    
    public boolean isDrawablePlayer() {
        return playerList
                .stream()
                .anyMatch(Player::isDrawable);
    }
    
    public ParticipantStatus getDrawablePlayerInfo() {
        return new ParticipantStatus(findDrawablePlayer());
    }
    
    private Player findDrawablePlayer() {
        return playerList.stream()
                .filter(Player::isDrawable)
                .findFirst()
                .orElse(null);
    }
    
    public void dontWantDraw() {
        Player drawablePlayer = findDrawablePlayer();
        if (drawablePlayer != null) {
            drawablePlayer.stop();
        }
    }
    
    public TotalWinningResult getWinningResult(Dealer dealer) {
        List<PlayerGameResult> playerGameResult = playerList
                .stream()
                .map(player -> player.determinePlayerResult(dealer))
                .toList();
        return getWinningResultWithDealerWinning(playerGameResult);
    }
    
    private TotalWinningResult getWinningResultWithDealerWinning(List<PlayerGameResult> playerGameResult) {
        int dealerWin = (int) playerGameResult
                .stream()
                .filter(result -> result.gameResult() == GameResult.LOSE)
                .count();
        int dealerDraw = (int) playerGameResult
                .stream()
                .filter(result -> result.gameResult() == GameResult.DRAW)
                .count();
        int dealerLose = (int) playerGameResult
                .stream()
                .filter(result -> result.gameResult() == GameResult.WIN)
                .count();
        return new TotalWinningResult(dealerWin, dealerDraw, dealerLose, playerGameResult);
    }
}
