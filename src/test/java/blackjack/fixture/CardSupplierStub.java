package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.strategy.CardSupplier;
import java.util.LinkedList;
import java.util.List;

public class CardSupplierStub implements CardSupplier {

    private final LinkedList<Card> cards = new LinkedList<>();

    public CardSupplierStub(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public static CardSupplierStub of(Card... cards) {
        List<Card> cardList = List.of(cards);
        return new CardSupplierStub(cardList);
    }

    @Override
    public Card getCard() {
        return cards.poll();
    }
}
