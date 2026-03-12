package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.List;
import java.util.stream.IntStream;

public class Players {
    
    private final List<Player> playerList;
    
    private Players(List<Player> playerList) {
        validateDuplicate(playerList);
        this.playerList = playerList;
    }
    
    public static Players fromNameAndBettingAmounts(List<String> playerNicknames, List<Integer> playerBettingAmounts) {
        validate(playerNicknames, playerBettingAmounts);
        
        List<Player> players = IntStream.range(0, playerNicknames.size())
                .mapToObj(index -> Player.from(playerNicknames.get(index), playerBettingAmounts.get(index)))
                .toList();
        return new Players(players);
    }
    
    private static void validate(List<String> playerNicknames, List<Integer> playerBettingAmounts) {
        if (playerNicknames.isEmpty()) {
            throw new IllegalArgumentException("한 명 이상의 플레이어 닉네임을 입력해주세요");
        }
        if (playerNicknames.size() != playerBettingAmounts.size()) {
            throw new IllegalArgumentException("플레이어의 수와 동일한 수의 배팅금이 필요합니다.");
        }
    }
    
    private void validateDuplicate(List<Player> playerList) {
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
