package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.cards.Cards;
import blackjack.model.cards.Score;

public class Gamer extends Player{

    private static final Score HIT_BOUNDARY = new Score(21);
    private static final int OPEN_CARD_COUNT = 2;

    private final Money bettingMoney;

    public Gamer(String name, Money bettingMoney, Card card1, Card card2, Card... cards) {
        super(name, card1, card2, cards);
        this.bettingMoney = bettingMoney;
    }

    public Money bettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean isHittable() {
        return score().lessThan(HIT_BOUNDARY);
    }

    @Override
    public Cards openCards() {
        return cards().openedCards(OPEN_CARD_COUNT);
    }
}
