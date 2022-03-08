package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Player {

    private final String name;
    private final List<Card> owningCards;
    private boolean turnState;

    public Player(final String name, final boolean turnState, final List<Card> owningCards) {
        Objects.requireNonNull(name, "[Error] 플레이어의 이름은 null이 들어올 수 없습니다.");
        Objects.requireNonNull(owningCards, "[Error] 보유 카드에는 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        this.name = name;
        this.turnState = turnState;
        this.owningCards = owningCards;
    }

    private void validateEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[Error] 플레이어의 이름은 공백이 들어올 수 없습니다.");
        }
    }

    public boolean canDraw() {
        return turnState;
    }

    public void draw(final Card card) {
        if (!canDraw()) {
            throw new IllegalStateException("[ERROR] 턴이 종료되었으면 카드를 받을 수 없습니다.");
        }
        owningCards.add(card);
    }

    public List<Card> cards() {
        return List.copyOf(owningCards);
    }

    public void endTurn() {
        turnState = false;
    }
}
