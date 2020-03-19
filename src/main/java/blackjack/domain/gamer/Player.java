package blackjack.domain.gamer;

import java.util.Objects;

public class Player extends Gamer {

    private final String name;

    public Player(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("비어있는 이름으로 player를 생성할 수 없습니다");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}