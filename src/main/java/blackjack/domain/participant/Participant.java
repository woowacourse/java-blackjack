package blackjack.domain.participant;

import blackjack.domain.Name;
import blackjack.domain.State;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final Name name;
    private final Cards cards;

    protected Participant(Name name, Cards cards) {
        Objects.requireNonNull(name, "[ERROR] 이름은 null일 수 없습니다.");
        Objects.requireNonNull(cards, "[ERROR] 카드들은 null일 수 없습니다.");

        this.name = name;
        this.cards = cards;
    }

    public void hit(CardDeck deck) {
        cards.add(deck.draw());
    }

    public boolean isBust() {
        return State.from(cards) == State.BUST;
    }

    public boolean isBlackJack() {
        return State.from(cards) == State.BLACKJACK;
    }

    public int getScore() {
        return cards.calculateScore()
                .getValue();
    }

    public List<Card> getCards() {
        return List.copyOf(cards.getCards());
    }

    public String getName() {
        return name.getValue();
    }

    abstract public boolean isHittable();
}
