package domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeckGenerator {

    private CardDeckGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static CardDeck create() {
        List<Card> cards = new ArrayList<>();

        for (CardType cardType : CardType.values()) {
            for (CardValue cardValue : CardValue.values()) {
                cards.add(new Card(cardType, cardValue));
            }
        }

        return CardDeck.createShuffled(cards);
    }
}
