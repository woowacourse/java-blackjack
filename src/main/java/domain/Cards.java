package domain;

import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int getSize() {
        return cards.size();
    }

    public Score getDealerScore() {
        int totalSumOfCards = getSumOfCard();
        int aceCount = getAceCount();

        return Score.ofDealerScore(totalSumOfCards, aceCount);
    }

    public Score getPlayerScore() {
        int totalSumOfCards = getSumOfCard();
        int aceCount = getAceCount();

        return Score.ofPlayerScore(totalSumOfCards, aceCount);
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int getSumOfCard() {
        return cards.stream()
                .mapToInt(Card::getLetterScore)
                .sum();
    }

    public void addNewCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getCard() {
        return cards.get(cards.size() - 1);
    }

    public void removeCard() {
        cards.remove(cards.size() - 1);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}
