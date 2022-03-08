package blackjack.domain;

public class Player implements Participant {

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름에 빈 값이 올 수 없습니다.");
        }
    }
}
