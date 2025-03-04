package blackjack.domain;

import java.util.HashSet;
import java.util.List;

public class Player {
    
    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 6;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 8;
    
    public Player(final String name) {
        validateName(name);
    }
    
    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
    
    public static void createPlayers(final List<String> playerNames) {
        validatePlayers(playerNames);
    }
    
    private static void validatePlayers(final List<String> playerNames) {
        if (hasDuplicatedName(playerNames)) {
            throw new IllegalArgumentException();
        }
        
        if (playerNames.size() < PLAYER_MIN_SIZE || playerNames.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException();
        }
    }
    
    private static boolean hasDuplicatedName(final List<String> playerNames) {
        return new HashSet<>(playerNames).size() != playerNames.size();
    }
}
