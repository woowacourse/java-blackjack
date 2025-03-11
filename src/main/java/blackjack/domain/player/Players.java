package blackjack.domain.player;

import blackjack.domain.card_hand.PlayerBettingBlackjackCardHand;
import blackjack.domain.deck.BlackjackDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    
    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 6;
    
    private final List<Player> players;
    
    private Players(final List<Player> players) {
        this.players = players;
    }
    
    public static Players from(final List<String> playerNames) {
        validatePlayerNames(playerNames);
        final List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        
        return new Players(players);
    }
    
    private static void validatePlayerNames(final List<String> playerNames) {
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
        long noDuplicatedNamesCount = playerNames.stream()
                .distinct()
                .count();
        return noDuplicatedNamesCount != playerNames.size();
    }

    public List<PlayerBettingBlackjackCardHand> toBlackjackBettingCardHand(final BlackjackDeck deck, final List<Integer> bettingAmounts) {
        final List<PlayerBettingBlackjackCardHand> bettingBlackjackCardHands = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            bettingBlackjackCardHands.add(PlayerBettingBlackjackCardHand.from(players.get(i), bettingAmounts.get(i), deck));
        }
        return Collections.unmodifiableList(bettingBlackjackCardHands);
    }
}
