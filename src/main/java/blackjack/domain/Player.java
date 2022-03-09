package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Player {

    private final String name;
    private final Cards owningCards;
    private boolean turnState;

    private Player(final String name, final boolean turnState, final Cards owningCards) {
        Objects.requireNonNull(name, "[Error] 플레이어의 이름은 null이 들어올 수 없습니다.");
        Objects.requireNonNull(owningCards, "[Error] 보유 카드에는 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        this.name = name;
        this.turnState = turnState;
        this.owningCards = owningCards;
    }

    public Player(final String name, final boolean turnState, final List<Card> owningCards) {
        this(name, turnState, new Cards(owningCards));
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
        validateEndTurn();
        owningCards.addCard(card);
        checkBust();
    }

    private void checkBust() {
        if (owningCards.isBust()) {
            endTurn();
        }
    }

    private void validateEndTurn() {
        if (!canDraw()) {
            throw new IllegalStateException("[ERROR] 턴이 종료되었으면 카드를 받을 수 없습니다.");
        }
    }

    public List<Card> cards() {
        return owningCards.cards();
    }

    public void endTurn() {
        turnState = false;
    }

    public int calculateResultScore() {
        validateCanCalculateResultScore();
        return owningCards.calculateScore();
    }

    private void validateCanCalculateResultScore() {
        if (canDraw()) {
            throw new IllegalStateException("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public List<Card> initCards() {
        return List.copyOf(cards().subList(0, 2));
    }
}
