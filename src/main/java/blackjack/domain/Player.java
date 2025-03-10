package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class Player {
    
    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 6;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 8;
    
    private final String name;
    
    public Player(final String name) {
        validateName(name);
        this.name = name;
    }
    
    private void validateName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("플레이어의 이름은 null이 될 수 없습니다.");
        }
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어의 이름은 %d자 이상, %d자 이하여야 합니다.".formatted(
                    MIN_NAME_LENGTH, MAX_NAME_LENGTH
            ));
        }
    }
    
    public static List<Player> createPlayers(final List<String> playerNames) {
        validatePlayers(playerNames);
        return playerNames.stream()
                .map(Player::new)
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
    
    public String getName() {
        return name;
    }
}
