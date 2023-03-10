package domain.game;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public final class Hand {

    private final List<Card> cards;
    private final GamePoint gamePoint;

    private Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.gamePoint = GamePoint.create(cards);
    }

    public static Hand create(final List<Card> cards) {
        return new Hand(cards);
    }

    public Hand add(final Card card) {
        List<Card> cardList = new ArrayList<>(cards);
        cardList.add(card);
        return new Hand(cardList);
    }

    public boolean isBlackJack() {
        return gamePoint.isSameAs(GamePoint.of(21)) && cards.size() == 2 ;
    }

    public boolean isBusted() {
        return gamePoint.isBusted();
    }

    public GamePoint getGamePoint() {
        return gamePoint;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public boolean isSameCount(final int count) {
        return this.cards.size() == count;
    }
}
