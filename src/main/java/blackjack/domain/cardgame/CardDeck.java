package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> deck;

    public CardDeck() {
        final List<Card> deck = new ArrayList<>();

        for (final CardNumber cardNumber : CardNumber.values()) {
            for (final CardShape cardShape : CardShape.values()) {
                deck.add(new Card(cardNumber, cardShape));
            }
        }

        Collections.shuffle(deck);

        this.deck = deck;
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 덱이 비어있어 카드를 뽑을 수 없습니다.");
        }
        return deck.remove(deck.size() - 1);
    }
}
