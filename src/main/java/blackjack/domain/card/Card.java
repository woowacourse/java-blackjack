package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Card {

    private static final String NO_CARD_ERROR_MESSAGE = "더 이상 뽑을 수 있는 카드가 없습니다.";

    private static final List<Card> cards = new LinkedList<>();

    static {
        Arrays.stream(Suit.values()).forEach((suit) -> {
            Arrays.stream(Denomination.values()).forEach((denomination -> {
                cards.add(new Card(denomination, suit));
            }));
        });
        Collections.shuffle(cards);
    }

    private final Denomination denomination;
    private final Suit suit;

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static Card draw() {
        try {
            return cards.remove(cards.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException(NO_CARD_ERROR_MESSAGE);
        }
    }

    public int getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return denomination == card.denomination && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
