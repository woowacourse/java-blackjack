package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.PlayerCards;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    private static final int ACE_DIFF = 10;
    protected static final int BLACKJACK = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final PlayerCards cards;
    private final Name name;

    protected AbstractPlayer(String name) {
        cards = new PlayerCards();
        this.name = new Name(name);
    }

    @Override
    public void drawCard(final Card card) {
        cards.add(card);
    }

    @Override
    abstract public boolean isCanDraw();

    @Override
    public int getScore() {
        int lowValue = getLowValue();
        int highValue = getHighValue(lowValue);
        if (highValue > BLACKJACK) {
            return lowValue;
        }
        return highValue;
    }

    private int getLowValue() {
        return cards.getCards().stream()
            .mapToInt(Card::getCardValue)
            .sum();
    }

    private int getHighValue(int lowValue) {
        int highValue = lowValue;
        if (cards.isContains(CardNumber.ACE)) {
            highValue += ACE_DIFF;
        }
        return highValue;
    }

    @Override
    public void drawRandomOneCard() {
        Cards cards = Cards.getInstance();
        drawCard(cards.draw());
    }

    @Override
    public void drawRandomTwoCards() {
        Cards cards = Cards.getInstance();
        drawCard(cards.draw());
        drawCard(cards.draw());
    }

    protected boolean isBlackJack() {
        return getScore() == BLACKJACK && cards.size() == BLACKJACK_CARD_SIZE;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }
}