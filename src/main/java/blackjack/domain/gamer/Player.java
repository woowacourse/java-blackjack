package blackjack.domain.gamer;

import blackjack.domain.dto.PlayerDto;

public class Player extends Gamer {

    private static final int HIT_THRESHOLD = 21;

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    @Override
    public boolean canHit() {
        return cards.sum() <= HIT_THRESHOLD;
    }

    public Name getName() {
        return name;
    }

    // TODO: DTO 변환 위치 재고
    public PlayerDto toDto() {
        return new PlayerDto(name, cards.getCards(), calculateScore());
    }
}
