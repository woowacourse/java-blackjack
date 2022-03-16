package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

import java.util.List;

public abstract class Participant {
    private static final int MAX_SCORE = 21;

    protected final String name;
    protected final Cards cards;

    protected Participant(final String name) {
        checkEmpty(name);
        this.name = name.trim();
        this.cards = new Cards();
    }

    protected Participant(final String name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    private void checkEmpty(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards.getCardGroup();
    }

    public boolean isBust() {
        return cards.sumScore() > MAX_SCORE;
    }

    public boolean isBlackjack() {
        return cards.sumScore() == MAX_SCORE && cards.hasOnlyStartCards();
    }

    public int getAddedCardCount() {
        return this.cards.getAddedCount();
    }

    public int sumScore() {
        return this.cards.sumScore();
    }

    public boolean isWinBy(Participant otherParticipant) {
        return this.cards.sumScore() > otherParticipant.sumScore();
    }

    public boolean isLoseBy(Participant otherParticipant) {
        return this.cards.sumScore() < otherParticipant.sumScore();
    }

    public boolean isDrawWith(Participant otherParticipant) {
        return this.cards.sumScore() == otherParticipant.sumScore();
    }

    public abstract boolean isFinish();

    public abstract Participant receive(final Card card);

    public abstract Participant hit(final Card card);
}
