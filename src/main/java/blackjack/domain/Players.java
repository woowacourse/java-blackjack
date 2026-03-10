package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
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
        playerList.forEach(player -> player.drawInitialCards(deck));
    }
    
    public List<Player> getPlayers() {
        return List.copyOf(playerList);
    }
    
    public Player getDrawablePlayer() {
        return playerList.stream()
                .filter(Player::isDrawable)
                .findFirst()
                .orElse(null);
    }
    
    public List<GameResult> determinePlayersGameResult(Dealer dealer) {
        return playerList.stream()
                .map(player -> player.calculateResult(dealer))
                .toList();
    }
}
