package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Collections;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    private static final int TWO_CARDS_SIZE = 2;

    private final Cards cards;
    private final Name name;

    public AbstractPlayer(String name) {
        cards = Cards.createEmptyCards();
        this.name = new Name(name);
    }

    public AbstractPlayer(Name name) {
        cards = Cards.createEmptyCards();
        this.name = name;
    }

    @Override
    public void drawOneCard(final Card card) {
        cards.add(card);
    }

    @Override
    public int getScore() {
        return cards.getScore();
    }

    @Override
    public void drawRandomOneCard(Cards allCards) {
        drawOneCard(allCards.drawOneCard());
    }

    @Override
    public void drawRandomTwoCards(Cards allCards) {
        for (int i = 0; i < TWO_CARDS_SIZE; i++) {
            drawOneCard(allCards.drawOneCard());
        }
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    @Override
    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
    }
}
