package domain.participant;

public class Player extends AbstractGambler {

    public Player(String name) {
        super(name);
        validateNotBlank(name);
        validateLength(name);
    }

    @Override
    public boolean canTakeMoreCard() {
        return (calculateScore() <= 21);
    }

    private void validateLength(String name) {
        if (name.length() > 10) {
            throw new IllegalArgumentException("플레이어의 이름은 10자를 넘을 수 없습니다.");
        }
    }

    private void validateNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 비어있을 수 없습니다.");
        }
    }
}
