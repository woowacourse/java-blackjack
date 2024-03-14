package model.participant;

import java.util.List;
import model.card.Cards;

public class Player extends Participant {

    private static final int HIT_CONDITION = 22;

    private final String name;

    public Player(String name) {
        this(name, new Cards(List.of()));
    }

    public Player(String name, Cards cards) {
        super(cards);
        validateEmptyName(name);
        this.name = name;
    }

    private void validateEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름은 빈 값일 수 없습니다.");
        }
    }

    @Override
    public boolean isPossibleHit() {
        int totalNumbers = cards.calculateTotalScore();
        return totalNumbers < HIT_CONDITION;
    }

    public String getName() {
        return name;
    }
}
