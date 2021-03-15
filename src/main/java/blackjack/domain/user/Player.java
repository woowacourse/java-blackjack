package blackjack.domain.user;

public class Player extends User {
    private final String name;
    private final int money;

    private Player(String name, int money) {
        validateNotEmptyName(name);
        this.name = name;
        this.money = money;
    }

    public static Player create(String name, int money) {
        return new Player(name, money);
    }

    private void validateNotEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("빈 이름이 입력되었습니다.");
        }
    }

    public int getMoney() {
        return money;
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
