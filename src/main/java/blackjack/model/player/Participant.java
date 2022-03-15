package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import java.util.List;

public abstract class Participant {
    protected static final int MAX_SCORE = 21;

    private final String name;
    protected final Cards cards;

    public Participant(final String name) {
        checkEmpty(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void checkEmpty(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
        }
    }

    public void receive(final Card card) {
        this.cards.add(card);
    }

    public boolean isBlackJack() {
        return cards.sumScore() == MAX_SCORE && cards.hasTwoCard();
    }

    public int countAddedCards() {
        return cards.countAddedCard();
    }

    public int sumCardsScore() {
        return cards.sumScore();
    }

    public boolean isBust() {
        return cards.sumScore() > MAX_SCORE;
    }

    public boolean isWinBy(Participant otherParticipant) {
        return cards.sumScore() > otherParticipant.cards.sumScore();
    }

    public boolean isDrawWith(Participant otherParticipant) {
        return cards.sumScore() == otherParticipant.cards.sumScore();
    }

    public List<String> getCards() {
        return cards.getValues();
    }

    public String getName() {
        return name;
    }

    public abstract boolean isImpossibleHit();
}
