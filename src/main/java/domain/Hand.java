package domain;

import domain.dto.CardDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hand implements Iterable<Card> {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(c -> c.getCardRank().equals(CardRank.ACE));
    }

    public List<CardDto> toCardDtos() {
        return cards.stream().map(Card::toCardDto).toList();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
