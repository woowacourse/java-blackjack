package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    protected static final int ACE_DIFF = 10;
    protected static final int BLACKJACK = 21;
    private static final int TWO_CARDS_SIZE = 2;

    private final List<Card> cards;
    private final Name name;

    public AbstractPlayer(String name) {
        cards = new ArrayList<>();
        this.name = new Name(name);
    }

    @Override
    public void drawOneCard(final Card card) {
        cards.add(card);
    }

    @Override
    abstract public boolean isCanDraw();

    @Override
    public int getScore() {
        int valueSum = cards.stream()
            .mapToInt(Card::getValue)
            .sum();
        if (valueSum + ACE_DIFF <= BLACKJACK) {
            return valueSum + ACE_DIFF;
        }
        return valueSum;
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
        return Collections.unmodifiableList(cards);
    }
}
