package blackjack.domain.participant;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public abstract class AbstractParticipant implements Participant {

    private static final int FIRST_DRAW_CARD_SIZE = 2;

    private final String name;
    private final Cards cards;
    private GameStatus gameStatus;

    public AbstractParticipant(final String name, final Cards cards, final GameStatus gameStatus) {
        Objects.requireNonNull(name, "플레이어의 이름은 null이 들어올 수 없습니다.");
        Objects.requireNonNull(cards, "보유 카드에는 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        this.name = name;
        this.cards = cards;
        this.gameStatus = gameStatus;
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
        if (!gameStatus.isFinishedGame()) {
            throw new IllegalStateException("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
        }
    }

    int calculateScore() {
        return cards.calculateScore();
    }

    @Override
    public void draw(final Card card) {
        if (gameStatus.isFinishedGame()) {
            throw new IllegalStateException("이미 턴이 종료되어 카드를 더 받을 수 없습니다.");
        }
        cards.addCard(card);
        refreshGameStatus();
    }

    private void refreshGameStatus() {
        if (isEnd()) {
            gameStatus = GameStatus.FINISHED;
        }
    }

    @Override
    public boolean canDraw() {
        return !gameStatus.isFinishedGame();
    }

    @Override
    public void endTurn() {
        gameStatus = GameStatus.FINISHED;
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
