package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.deck.DeckStrategy;

import java.util.ArrayList;
import java.util.List;

public class TestDeckForThreeParticipant implements DeckStrategy {

    private final List<Card> cards = new ArrayList<>();
    private final List<Card> initialCards = new ArrayList<>();

    public TestDeckForThreeParticipant() {
        for (CardShape shape : CardShape.values()) {
            initDeck(shape);
        }
        // 딜러 카드
        initialCards.add(Card.of(CardShape.HEART, CardNumber.of(7)));
        initialCards.add(Card.of(CardShape.HEART, CardNumber.of(8)));

        // player1 : 블랙잭
        initialCards.add(Card.of(CardShape.HEART, CardNumber.of(1)));
        initialCards.add(Card.of(CardShape.HEART, CardNumber.of(10)));

        // player2
        initialCards.add(Card.of(CardShape.HEART, CardNumber.of(2)));
        initialCards.add(Card.of(CardShape.HEART, CardNumber.of(5)));
    }

    private void initDeck(final CardShape cardShape) {
        for (int i = CardNumber.getMinValue(); i <= CardNumber.getMaxValue(); i++) {
            cards.add(Card.of(cardShape, CardNumber.of(i)));
        }
    }

    @Override
    public Card drawCard() {
        if (!initialCards.isEmpty()) {
            final Card card = initialCards.get(0);
            initialCards.remove(card);
            return drawSpecificCard(card);
        }
        try {
            return cards.remove(cards.size() - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalStateException("덱에 더이상 카드가 남아있지 않습니다.", exception);
        }
    }

    public Card drawSpecificCard(final Card card) {
        if (cards.contains(card)) {
            cards.remove(card);
            return card;
        }
        throw new IllegalArgumentException("찾는 카드가 덱에 없습니다.");
    }
}
