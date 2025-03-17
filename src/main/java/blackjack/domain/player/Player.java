package blackjack.domain.player;

import java.util.Objects;

public final class Player {

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
    
    public boolean isSameName(String name) {
        return this.name.equals(name);
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
