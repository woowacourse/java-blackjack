package domain;

import domain.card.Card;
import domain.player.HandCards;
import domain.vo.Name;

abstract class Participant {
    private Name name;
    private HandCards handCards;

    abstract void drawCard(Card card);
    abstract int getCardsSum();
    abstract public boolean isBust();
}
