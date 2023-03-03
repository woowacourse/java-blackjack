package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomDeck implements Deck {

    private final List<Card> cards;

    public RandomDeck() {
        final List<Card> allKindCard = getAllKindCard();
        Collections.shuffle(allKindCard);
        this.cards = allKindCard;
    }

    private List<Card> getAllKindCard() {
        final ArrayList<Card> cardsData = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            makeCardsOf(cardsData, shape);
        }
        return cardsData;
    }

    private void makeCardsOf(List<Card> cardData, final Shape shape) {
        for (int i = CardNumber.MIN_RANGE; i <= CardNumber.MAX_RANGE; i++) {
            cards.add(new Card(shape, CardNumber.of(i)));
        }
    }

    @Override
    public Card drawCard() {
        try {
            return cards.remove(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalStateException("카드 업슝", exception);
        }
    }

}
