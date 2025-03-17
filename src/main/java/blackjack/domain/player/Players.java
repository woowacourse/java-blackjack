package blackjack.domain.player;

import blackjack.domain.BettingBoard;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import blackjack.domain.deck.BlackjackCardHandInitializer;

import blackjack.domain.money.BlackjackBettingMoney;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public final class Players {
    
    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 6;
    private final List<Player> players;
    
    private Players(final List<Player> players) {
        this.players = players;
    }
    
    public static Players createPlayers(final List<String> playerNames) {
        validatePlayers(playerNames);
        return new Players(playerNames.stream()
                .map(Player::new)
                .toList());
    }
    
    public void bet(Map<String, BlackjackBettingMoney> bettings, BettingBoard bettingBoard) {
        for (String playerName : bettings.keySet()) {
            Player player = findPlayerByName(playerName);
            bettingBoard.bet(player, bettings.get(playerName));
        }
    }
    
    private Player findPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니다."));
    }
    
    public List<PlayerBlackjackCardHand> drawCardsAndGetCardHands(final BlackjackCardHandInitializer initializer) {
        return players.stream()
                .map(player -> new PlayerBlackjackCardHand(player, initializer))
                .toList();
    }
    
    private static void validatePlayers(final List<String> playerNames) {
        if (hasDuplicatedName(playerNames)) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
        
        if (playerNames.size() < PLAYER_MIN_SIZE || playerNames.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException("플레이어 인원수는 %d명부터 %d명까지 입니다.".formatted(
                    PLAYER_MIN_SIZE, PLAYER_MAX_SIZE
            ));
        }
    }
    
    private static boolean hasDuplicatedName(final List<String> playerNames) {
        return new HashSet<>(playerNames).size() != playerNames.size();
    }
}
