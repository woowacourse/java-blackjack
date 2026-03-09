package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayingCards {
    
    private final List<Card> cards;
    
    public PlayingCards() {
        this.cards = new ArrayList<>();
    }
    
    private PlayingCards(List<Card> cards) {
        this.cards = cards;
    }
    
    public static PlayingCards from(List<Card> cards) {
        return new PlayingCards(cards);
    }
    
    public static PlayingCards createdDeck() {
        List<Card> deck = new ArrayList<>();
        List<Rank> ranks = Arrays
                .stream(Rank.values())
                .toList();
        List<Suit> suits = Arrays
                .stream(Suit.values())
                .toList();
        for (Rank rank : ranks) {
            matchDenominationWithSymbol(rank, suits, deck);
        }
        return from(deck);
    }
    
    public static PlayingCards createShuffledDeck() {
        PlayingCards deck = createdDeck();
        return deck.shuffle();
    }
    
    private static void matchDenominationWithSymbol(Rank rank, List<Suit> suits, List<Card> deck) {
        for (Suit suit : suits) {
            Card card = new Card(rank, suit);
            deck.add(card);
        }
    }
    
    public PlayingCards shuffle() {
        List<Card> copiedCards = new ArrayList<>(getCards());
        Collections.shuffle(copiedCards);
        return from(copiedCards);
    }

    public PlayingCards drawCard() {
        return drawCards(1);
    }
    
    public PlayingCards drawCards(int count) {
        hasEnoughCard(count);

        List<Card> drewCards = new ArrayList<>(cards.subList(0, count));
        cards.subList(0, count).clear();
        
        return new PlayingCards(drewCards);
    }
    
    private void hasEnoughCard(int count) {
        if (cards.size() < count) {
            throw new IllegalArgumentException("남은 카드가 없습니다.");
        }
    }

    public String getFirstSnapshot() {
        return cards.getFirst().getDisplayName();
    }

    public String getSnapshot() {
        return cards
                .stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean add(PlayingCards receivedCards) {
        return this.cards.addAll(receivedCards.getCards());
    }

    public boolean isCardRemain() {
        return !cards.isEmpty();
    }
}
