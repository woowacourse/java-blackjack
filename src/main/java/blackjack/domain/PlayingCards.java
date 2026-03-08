package blackjack.domain;

import blackjack.dto.DrawResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayingCards {
    
    private final int BUSTED_SCORE = 21;
    private final int DEALER_SCORE = 16;
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
    
    public PlayingCards add(PlayingCards cards) {
        List<Card> copiedCards = new ArrayList<>(getCards());
        copiedCards.addAll(cards.getCards());
        return from(copiedCards);
    }
    
    public DrawResult draw() {
        return draw(1);
    }
    
    public DrawResult draw(int count) {
        hasEnoughCard(count);
        
        List<Card> drewCards = new ArrayList<>(cards.subList(0, count));
        List<Card> remainCards = new ArrayList<>(cards.subList(count, cards.size()));
        
        return DrawResult.of(from(drewCards), from(remainCards));
    }
    
    private void hasEnoughCard(int count) {
        if (cards.size() < count) {
            throw new IllegalArgumentException("남은 카드가 없습니다.");
        }
    }
    
    public String getFirstCardDisplayName() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("남은 카드가 없습니다.");
        }
        return cards.getFirst().getDisplayName();
    }
    
    public boolean isDealerDraw() {
        return calculateScoreSum() <= DEALER_SCORE;
    }
    
    public boolean isNotDrawable() {
        return !isDrawable();
    }
    
    public boolean isDrawable() {
        return calculateTotalScore() < BUSTED_SCORE;
    }
    
    public boolean isBusted(int scoreSum) {
        return scoreSum > BUSTED_SCORE;
    }
    
    public int calculateTotalScore() {
        int scoreSum = calculateScoreSum();
        boolean busted = isBusted(scoreSum);
        if (busted) {
            int aceCount = countAce();
            return calculateWithAce(scoreSum, aceCount);
        }
        return scoreSum;
    }
    
    public int calculateTotalScoreForResult() {
        int scoreSum = calculateScoreSum();
        boolean busted = isBusted(scoreSum);
        if (busted) {
            int aceCount = countAce();
            return bustedScore(scoreSum, aceCount);
        }
        return scoreSum;
    }
    
    private int bustedScore(int scoreSum, int aceCount) {
        int totalScore = scoreSum;
        if (aceCount > 0) {
            totalScore = calculateWithAce(scoreSum, aceCount);
        }
        if (totalScore <= BUSTED_SCORE) {
            return totalScore;
        }
        return 0;
    }
    
    public String getStatusByDisplayName() {
        return cards.stream().map(Card::getDisplayName).collect(Collectors.joining(", "));
    }
    
    private int calculateScoreSum() {
        return cards
                .stream()
                .mapToInt(Card::getScore)
                .sum();
    }
    
    private int countAce() {
        return (int) cards
                .stream()
                .filter(Card::isAce)
                .count();
    }
    
    private int calculateWithAce(int scoreSum, int aceCount) {
        if (aceCount == 0) {
            return scoreSum;
        }
        int calculatedScore = scoreSum - 10;
        if (calculatedScore <= BUSTED_SCORE) {
            return calculatedScore;
        }
        return calculateWithAce(calculatedScore, aceCount - 1);
    }
    
    private List<Card> getCards() {
        return List.copyOf(cards);
    }
}
