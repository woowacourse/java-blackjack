package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.exception.ErrorException;
import java.util.List;

public abstract class Participant {

    protected final String name;
    protected final Cards cards;

    protected Participant(final String name) {
        validateBlank(name);
        this.name = name;
        this.cards = new Cards();
    }

    public abstract List<Card> getInitialCards();

    public abstract boolean ableToAddCard();

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust(cards.calculateScore());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new ErrorException("참여자 이름은 공백일 수 없습니다.");
        }
    }
}
