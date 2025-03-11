package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck shuffleCardDeck() {
        List<Card> shuffledCards = Card.values();
        Collections.shuffle(shuffledCards);
        return new CardDeck(shuffledCards);
    }

    public Card pickRandomCard() {
        try {
            return cards.removeFirst();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("카드덱의 카드를 모두 소진하여 더이상 카드를 뽑을 수 없습니다.");
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
