package blackjack.domain.participant;

import java.util.List;
import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {

    private static final int BLACKJACK_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;

    private final Name name;
    private final Cards cards;

    protected Participant(Name name, Cards cards) {
        validateName(name);
        validateCards(cards);
        this.name = name;
        this.cards = cards;
    }

    private void validateCards(Cards cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드는 null 일 수 없습니다.");
    }

    private void validateName(Name name) {
        Objects.requireNonNull(name, "[ERROR] 이름은 null 일 수 없습니다.");
    }

    public void hit(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBust() {
        return cards.isOverScoreThan(BLACKJACK_SCORE);
    }

    public boolean isBlackJack() {
        return cards.isSameSize(BLACKJACK_COUNT) && cards.isSameScore(BLACKJACK_SCORE);
    }

    abstract public boolean isHittable();

    public List<Card> getCards() {
        return cards.getValues();
    }

    public String getName() {
        return name.getValue();
    }
}
