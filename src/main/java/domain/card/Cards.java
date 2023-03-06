package domain.card;

import domain.game.GamePoint;

import java.util.ArrayList;
import java.util.List;

public final class Cards {

    private final List<Card> cards;
    private final GamePoint gamePoint;

    private Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.gamePoint = GamePoint.create(cards);
    }

    public static Cards create(final List<Card> cards) {
        return new Cards(cards);
    }

    public Cards add(final Card card) {
        List<Card> cardList = new ArrayList<>(cards);
        cardList.add(card);
        return new Cards(cardList);
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
}
