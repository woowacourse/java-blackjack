package object.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import object.game.Score;

public class CardDeck {
    private static final int BUST_THRESHOLD = 21;

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

    public Score getScore() {
        int totalScore = 0;
        int aceCount = 0;

        for (Card card : cards) {
            totalScore += card.getNumber();
            if (card.isAceCard()) {
                aceCount++;
            }
        }

        while (aceCount-- > 0) {
            if (totalScore + 10 <= BUST_THRESHOLD) {
                totalScore += 10;
            }
        }

        return Score.of(totalScore, cards.size());
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 모두 소진되어서, 더 이상 드로우 할 수 없습니다.");
        }
        return cards.removeFirst();
    }

    public void addCard(Card card) {
        cards.add(card);
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
