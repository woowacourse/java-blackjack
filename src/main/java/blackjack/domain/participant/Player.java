package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;
import java.util.List;
import java.util.Objects;

public class Player {

    private final Cards owningCards;
    private final String name;
    private boolean turnState;

    private Player(final String name, final boolean turnState, final Cards owningCards) {
        Objects.requireNonNull(name, "플레이어의 이름은 null이 들어올 수 없습니다.");
        Objects.requireNonNull(owningCards, "보유 카드에는 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        this.owningCards = owningCards;
        this.name = name;
        this.turnState = turnState;
    }

    public Player(final String name, final boolean turnState, final List<Card> owningCards) {
        this(name, turnState, new Cards(owningCards));
    }

    private void validateEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백이 들어올 수 없습니다.");
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

    private void validateEndTurn() {
        if (!canDraw()) {
            throw new IllegalStateException("턴이 종료되었으면 카드를 받을 수 없습니다.");
        }
    }

    private void checkBust() {
        if (owningCards.isBust()) {
            endTurn();
        }
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

    public GameOutcome fightResult(final Dealer dealer) {
        return owningCards.fightResult(new Cards(dealer.getCards()));
    }

    public List<Card> getCards() {
        return List.copyOf(owningCards.cards());
    }

    public String getName() {
        return name;
    }
}
