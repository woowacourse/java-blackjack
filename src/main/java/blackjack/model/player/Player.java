package blackjack.model.player;

import blackjack.model.card.Cards;
import blackjack.model.card.Score;
import blackjack.model.cardgenerator.CardGenerator;

import java.util.Objects;

public class Player {
    private final Name name;
    private final Cards cards;

    public Player(final String name, final CardGenerator cardGenerator) {
        this.name = new Name(name);
        this.cards = Cards.deal(cardGenerator);
    }

    public Score calculateCardsTotalScore() {
        return cards.calculateCardsTotalScore();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean canHit() {
        return !cards.isBust();
    }

    public void hit(final CardGenerator cardGenerator) {
        cards.addCard(cardGenerator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Cards getCards() {
        return cards;
    }

    public Name getName() {
        return name;
    }
}
