package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CardDeck {
    private final List<Card> cards;

    private CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public CardDeck(final CardDeck cardDeck) {
        this.cards = new ArrayList<>(cardDeck.cards);
    }

    public static CardDeck generateEmptySet() {
        return new CardDeck(new ArrayList<>());
    }

    public static CardDeck generateFullPlayingCard() {
        //52장 만들기
        List<Card> initializePlayingCard = initializePlayingCard();
        return new CardDeck(initializePlayingCard);
    }

    private static List<Card> initializePlayingCard() {
        List<Card> initCard = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardSymbol cardSymbol : CardSymbol.values()) {
                initCard.add(new Card(cardNumber, cardSymbol));
            }
        }
        return initCard;
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card draw() {
        return cards.removeFirst();
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardDeck cardDeck = (CardDeck) o;
        return Objects.equals(cards, cardDeck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
