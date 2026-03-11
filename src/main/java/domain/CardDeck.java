package domain;

import java.util.ArrayList;
import java.util.List;

// FIXME: Deck 만으로도 의미 전달 충분
// TODO: draw() 책임을 갖도록 리팩터링
// TODO: shuffle()이 여기 있으면 자연스러우나, 테스트 측면에서 어떻게 할 것인가?
public class CardDeck {
    // FIXME: Deque로 개선
    private final List<Card> cardDeck;

    private CardDeck(final List<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public static CardDeck initCardDeck() {
        final List<Card> cards = new ArrayList<>();
        for (final CardSuit suit : CardSuit.values()) {
            addCard(suit, cards);
        }
        return new CardDeck(cards);
    }

    private static void addCard(final CardSuit suit, final List<Card> cards) {
        for (final CardRank rank : CardRank.values()) {
            cards.add(new Card(suit, rank));
        }
    }

    // FIXME: 카드의 인덱스 접근이 의미상 부자연스러움
    public void removeCardOf(final int index) {
        cardDeck.remove(index);
    }

    public List<Card> getCardDeck() {
        return List.copyOf(cardDeck);
    }

    public int getDeckSize() {
        return cardDeck.size();
    }

    public Card getCardOf(final int index) {
        return cardDeck.get(index);
    }
}
