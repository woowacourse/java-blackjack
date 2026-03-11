package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.List;

public class Players {
    
    private final List<Player> playerList;
    
    private Players(List<Player> playerList) {
        validate(playerList);
        this.playerList = playerList;
    }
    
    public static Players fromNames(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }
    
    private void validate(List<Player> playerList) {
        long distinctCount = playerList.stream()
                .map(Player::getNickname)
                .distinct()
                .count();
        if (distinctCount != playerList.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
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
}
