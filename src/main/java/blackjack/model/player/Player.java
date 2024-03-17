package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.card.Score;
import blackjack.model.cardgenerator.CardGenerator;

import java.util.List;

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

    public List<Card> getCards() {
        return cards.getCards();
    }

    public Name getName() {
        return name;
    }
}
