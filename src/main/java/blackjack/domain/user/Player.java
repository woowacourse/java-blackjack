package blackjack.domain.user;

public class Player extends User {
    private final String name;

    private Player(String name) {
        validateNotEmptyName(name);
        this.name = name;
    }

    public static Player create(String name) {
        return new Player(name);
    }

    private void validateNotEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("빈 이름이 입력되었습니다.");
        }
    }

    @Override
    public boolean isHit() {
        return hand.isSameStatus(HandStatus.HIT);
    }

    @Override
    public String getName() {
        return name;
    }
}
