package blackjack.domain.player;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public abstract class AbstractParticipant implements Participant {

    private static final int FIRST_DRAW_CARD_SIZE = 2;

    private final String name;
    private final Cards cards;
    private boolean turnState;

    public AbstractParticipant(final String name, final Cards cards, final boolean turnState) {
        Objects.requireNonNull(name, "플레이어의 이름은 null이 들어올 수 없습니다.");
        Objects.requireNonNull(cards, "보유 카드에는 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        this.name = name;
        this.cards = cards;
        this.turnState = turnState;
    }

    private void validateEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백이 들어올 수 없습니다.");
        }
    }

    @Override
    public int calculateResultScore() {
        validateCanCalculateResultScore();
        return cards.calculateScore();
    }

    private void validateCanCalculateResultScore() {
        if (turnState) {
            throw new IllegalStateException("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
        }
    }

    int calculateScore() {
        return cards.calculateScore();
    }

    @Override
    public void draw(final Card card) {
        if (!turnState) {
            throw new IllegalStateException("이미 턴이 종료되어 카드를 더 받을 수 없습니다.");
        }
        cards.addCard(card);
        checkEndGame();
    }

    private void checkEndGame() {
        if (isEnd()) {
            turnState = false;
        }
    }

    @Override
    public boolean canDraw() {
        return turnState;
    }

    @Override
    public void endTurn() {
        turnState = false;
    }

    @Override
    public GameOutcome fightResult(final Participant participant) {
        validateFightGame(participant);
        return cards.fightResult(new Cards(participant.cards()));
    }

    private void validateFightGame(final Participant participant) {
        if (this.canDraw() || participant.canDraw()) {
            throw new IllegalStateException("턴이 종료되지 않아 비교할 수 없습니다.");
        }
    }

    @Override
    public List<Card> cards() {
        return List.copyOf(cards.cards());
    }

    @Override
    public String getName() {
        return name;
    }

    abstract boolean isEnd();
}
