package blackjack.domain.player;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    protected static final int ACE_DIFF = 10;
    protected static final int BLACKJACK = 21;
    private static final int FIRST_DRAW_CARDS_COUNT = 2;

    private final List<Card> cards;
    private final Name name;
    private boolean isDrawStop = false;

    public AbstractPlayer(String name) {
        cards = new ArrayList<>();
        this.name = new Name(name);
    }

    @Override
    public void drawCard(final Card card) {
        cards.add(card);
    }

    @Override
    public boolean isCanDraw() {
        return false;
    }

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
            .mapToInt(card->card.getCardNumber().getValue())
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
    public void stopDraw() {
        isDrawStop = true;
    }

    @Override
    public void draw() {
        Cards cards = Cards.getCards();
        drawCard(cards.draw());
    }

    @Override
    public void drawTwoCards() {
        Cards cards = Cards.getCards();
        for (int i = 0; i < FIRST_DRAW_CARDS_COUNT; i++) {
            drawCard(cards.draw());
        }
    }

    public ResultType getResult(Dealer dealer) {
        int userValue = getValue();
        int dealerValue = dealer.getValue();
        if (userValue > BLACKJACK || userValue < dealerValue) {
            return ResultType.LOSS;
        }
        if (userValue == dealerValue) {
            return ResultType.DRAW;
        }
        return ResultType.WIN;
    }

    @Override
    public boolean isDrawStop() {
        return isDrawStop;
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
