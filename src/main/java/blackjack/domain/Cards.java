package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;
    private final CardPickMachine cardPickMachine;

    public Cards(CardPickMachine cardPickMachine) {
        this.cards = makeCards();
        this.cardPickMachine = cardPickMachine;
    }

    public Card assignCard() {
        return cards.get(cardPickMachine.assignIndex());
    }

    private List<Card> makeCards() {
        List<Card> allCard = new ArrayList<>();
        List<Suit> suits = Arrays.stream(Suit.values())
                .collect(Collectors.toList());
        List<Symbols> symbols = Arrays.stream(Symbols.values())
                .collect(Collectors.toList());

        for (Suit suit : suits) {
            addRankForSuit(allCard, symbols, suit);
        }
        return allCard;
    }

    private void addRankForSuit(List<Card> allCard, List<Symbols> symbols, Suit suit) {
        for (Symbols symbol : symbols) {
            allCard.add(new Card(suit, symbol));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cards cards1 = (Cards) o;

        return cards != null ? cards.equals(cards1.cards) : cards1.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
