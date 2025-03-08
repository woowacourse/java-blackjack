package domain;

import java.util.Objects;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    @Override
    public void prepareGame(Cards totalCard) {
        hit(totalCard);
        hit(totalCard);
    }

    public void hit(Cards totalCards) {
        validateBurst();
        add(totalCards);
    }

    private void validateBurst() {
        if (isBurst()) {
            throw new IllegalStateException("[ERROR] 버스트되면 카드를 뽑을 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
