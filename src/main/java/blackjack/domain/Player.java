package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

import java.util.List;

public class Player extends Participant {
    private static final int INITIAL_OPEN_CARD_COUNT = 2;

    private final BettingMoney bettingMoney;

    protected Player(final ParticipantCards cards, final String name, final int bettingMoney) {
        super(cards, name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public int getBettingMoney() {
        return bettingMoney.getValue();
    }

    @Override
    public List<Card> initialOpen() {
        return cards.open(INITIAL_OPEN_CARD_COUNT);
    }

    @Override
    protected boolean isHittable() {
        return !cards.isBlackJack() && !cards.isBust();
    }
}
