package blackjack.domain.gamer;

public class Player extends Person {

    public Player(String name) {
        validateNameLength(name);
        super.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException("유효하지 않은 플레이어 이름입니다.");
        }
    }
}
