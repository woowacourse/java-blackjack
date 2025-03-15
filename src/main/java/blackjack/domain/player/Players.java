package blackjack.domain.player;

import blackjack.domain.card_hand.PlayerBettingBlackjackCardHand;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.util.GlobalValidator;

import java.util.List;
import java.util.Map;

public final class Players {
    
    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 6;
    
    private final List<Player> players;
    
    private Players(final List<Player> players) {
        GlobalValidator.validateNotNull(Players.class, players);
        this.players = players;
    }
    
    public static Players from(final List<String> playerNames) {
        GlobalValidator.validateNotNull(Players.class, playerNames);
        validatePlayerNamesNotDuplicated(playerNames);
        validatePlayerNamesSize(playerNames);
        final List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        
        return new Players(players);
    }
    
    private static void validatePlayerNamesNotDuplicated(final List<String> playerNames) {
        if (hasDuplicatedName(playerNames)) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }
    
    private static void validatePlayerNamesSize(final List<String> playerNames) {
        if (playerNames.size() < PLAYER_MIN_SIZE || playerNames.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException("플레이어 인원수는 %d명부터 %d명까지 입니다.".formatted(
                    PLAYER_MIN_SIZE, PLAYER_MAX_SIZE
            ));
        }
    }
    
    private static boolean hasDuplicatedName(final List<String> playerNames) {
        long noDuplicatedNamesCount = playerNames.stream()
                .distinct()
                .count();
        return noDuplicatedNamesCount != playerNames.size();
    }
    
    public List<PlayerBettingBlackjackCardHand> toBlackjackBettingCardHand(
            final BlackjackDeck deck,
            final Map<String, Integer> bettingAmounts
    ) {
        if (!isBettingAmountAllPresent(bettingAmounts)) {
            throw new IllegalArgumentException("베팅 금액이 없는 플레이어가 존재합니다.");
        }
        return players.stream()
                .map(player -> PlayerBettingBlackjackCardHand.createWithInitialCards(
                        player,
                        bettingAmounts.get(player.getName()),
                        deck))
                .toList();
    }
    
    private boolean isBettingAmountAllPresent(final Map<String, Integer> bettingAmounts) {
        return bettingAmounts.keySet().containsAll(getPlayerNames());
    }
    
    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
