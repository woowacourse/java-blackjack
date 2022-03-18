package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.strategy.CardSupplier;
import java.util.LinkedList;
import java.util.List;

public class CardDeckStub implements CardSupplier {

    private final LinkedList<Card> cards = new LinkedList<>();

    public CardDeckStub(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public static CardDeckStub of(Card... cards) {
        List<Card> cardList = List.of(cards);
        return new CardDeckStub(cardList);
    }

    @Override
    public Card getCard() {
        return cards.poll();
    }
}
