package blackjack.domain.player;

import blackjack.util.GlobalValidator;

public final class Player {
    
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 8;
    
    private final String name;
    
    public Player(final String name) {
        GlobalValidator.validateNotNull(Player.class, name);
        validateNameLength(name);
        this.name = name;
    }
    
    private void validateNameLength(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어의 이름은 %d자 이상, %d자 이하여야 합니다.".formatted(
                    MIN_NAME_LENGTH, MAX_NAME_LENGTH
            ));
        }
    }
    
    public String getName() {
        return name;
    }
}
