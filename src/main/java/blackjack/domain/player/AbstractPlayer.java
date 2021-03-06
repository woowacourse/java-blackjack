package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    protected static final int ACE_DIFF = 10;
    protected static final int BLACKJACK = 21;

    private final List<Card> cards;
    private final Name name;

    public AbstractPlayer(String name) {
        cards = new ArrayList<>();
        this.name = new Name(name);
    }

    @Override
    public void drawCard(final Card card) {
        cards.add(card);
    }

    @Override
    abstract public boolean isCanDraw();

    @Override
    public int getValue() {
        int lowValue = getLowValue();
        int highValue = getHighValue(lowValue);
        if (highValue > BLACKJACK) {
            return lowValue;
        }
        return highValue;
    }

    private int getLowValue() {
        return cards.stream()
            .mapToInt(card -> card.getCardNumber().getValue())
            .sum();
    }

    private int getHighValue(int lowValue) {
        int highValue = lowValue;
        if (cards.stream().anyMatch(card -> card.getCardNumber() == CardNumber.ACE)) {
            highValue += ACE_DIFF;
        }
        return highValue;
    }

    @Override
    public void drawRandomOneCard() {
        Cards cards = Cards.getCards();
        drawCard(cards.draw());
    }

    @Override
    public void drawRandomTwoCards() {
        Cards cards = Cards.getCards();
        drawCard(cards.draw());
        drawCard(cards.draw());
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