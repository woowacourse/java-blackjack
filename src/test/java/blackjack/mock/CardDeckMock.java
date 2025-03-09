package blackjack.mock;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CardDeckMock extends CardDeck {

    private final Queue<Card> mockedCard;

    public CardDeckMock(Queue<Card> mockedCard) {
        this.mockedCard = mockedCard;
    }

    @Override
    public int getSize() {
        return mockedCard.size();
    }

    @Override
    public List<Card> drawCard(int count) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawnCards.add(mockedCard.poll());
        }
        return drawnCards;
    }
}
