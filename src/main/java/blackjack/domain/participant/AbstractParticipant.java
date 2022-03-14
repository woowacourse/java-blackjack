package blackjack.domain.participant;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public abstract class AbstractParticipant implements Participant {

    private final String name;
    private final Cards cards;
    private ParticipantStatus participantStatus;

    AbstractParticipant(final String name, final Cards cards, final ParticipantStatus participantStatus) {
        Objects.requireNonNull(name, "플레이어의 이름은 null이 들어올 수 없습니다.");
        Objects.requireNonNull(cards, "보유 카드에는 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        this.name = name;
        this.cards = cards;
        this.participantStatus = participantStatus;
    }

    private void validateEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백이 들어올 수 없습니다.");
        }
    }

    @Override
    public void hit(final Card card) {
        validateCanHit();
        cards.addCard(card);
        refreshParticipantStatus();
    }

    private void validateCanHit() {
        if (!canHit()) {
            throw new IllegalStateException("이미 턴이 종료되어 카드를 더 받을 수 없습니다.");
        }
    }

    private void refreshParticipantStatus() {
        participantStatus = participantStatus.refreshStatus(cards);
        if (isDealer() && isEnd()) {
            participantStatus = ParticipantStatus.STAND;
        }
    }

    @Override
    public boolean canHit() {
        return !participantStatus.isFinishedGame();
    }

    @Override
    public void changeFinishStatus() {
        if (canHit()) {
            participantStatus = ParticipantStatus.STAND;
        }
    }

    @Override
    public int calculateResultScore() {
        validateCanCalculateResultScore();
        return calculateScore();
    }

    private void validateCanCalculateResultScore() {
        if (canHit()) {
            throw new IllegalStateException("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
        }
    }

    int calculateScore() {
        if (isDealer()) {
            return cards.calculateMaxScore();
        }
        return cards.calculateScore();
    }

    @Override
    public GameOutcome fight(final Participant participant) {
        validateFightGame(participant);
        return GameOutcome.calculateOutcome(this, participant);
    }

    private void validateFightGame(final Participant participant) {
        if (this.canHit() || participant.canHit()) {
            throw new IllegalStateException("턴이 종료되지 않아 비교할 수 없습니다.");
        }
    }

    @Override
    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
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
