package blackJack.domain.card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Card {

    private static final String ERROR_MESSAGE_NOT_EXIST_CARD = "존재하지 않는 카드입니다.";

    private static final List<Card> CARDS = List.copyOf(initCards());

    private final Symbol symbol;
    private final Denomination denomination;

    private Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

    private static List<Card> initCards() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            initCardsBySymbol(cards, symbol);
        }
        return cards;
    }

    private static void initCardsBySymbol(List<Card> cards, Symbol symbol) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(symbol, denomination));
        }
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public static List<Card> newDeck() {
        return new LinkedList<>(CARDS);
    }

    public static Card from(Symbol symbol, Denomination denomination) {
        return CARDS.stream()
                .filter(card -> card.symbol == symbol && card.denomination == denomination)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_NOT_EXIST_CARD));
    }

    public String getCardInfo() {
        return denomination.getDenomination() + symbol.getName();
    }

    public int getScore() {
        return denomination.getScore();
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
        return symbol == card.symbol && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, denomination);
    }
}
