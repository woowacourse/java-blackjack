package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.Score;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private final Name name;
    private final Cards cards;

    protected Participant(String name, List<Card> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public void addCard(Card card) {
        this.cards.addCard(card);
    }

    public Score getScore() {
        return cards.calculateScore();
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public abstract boolean isPlayer();

    public abstract boolean isDealer();

    public abstract List<Card> getStartCards();

    public abstract boolean canDrawCard();

    public boolean isSameName(Name name) {
        return Objects.equals(this.name, name);
    }
}
