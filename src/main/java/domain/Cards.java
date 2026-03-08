package domain;

import domain.dto.CardDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Cards implements Iterable<Card> {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pop() {
        return cards.removeFirst();

        // 리스트 비었을 때 처리 필요
    }

    public int getSize() {
        return cards.size();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    public List<CardDto> toCardDtos() {
        return cards.stream().map(Card::toCardDto).toList();
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(c -> c.getCardRank().equals(CardRank.ACE));
    }
}
