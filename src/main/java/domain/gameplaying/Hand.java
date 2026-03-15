package domain.gameplaying;

import domain.common.BlackJackRule;
import dto.CardInfo;
import java.util.ArrayList;
import java.util.List;

class Hand {

    private final BlackJackDeck deck;
    private final List<Card> cards;

    Hand(BlackJackDeck deck, List<Card> cards) {
        this.deck = deck;
        this.cards = new ArrayList<>(cards);
    }

    static Hand with(BlackJackDeck deck) {
        return new Hand(deck, new ArrayList<>());
    }

    void drawCard() {
        cards.add(deck.draw());
    }

    List<CardInfo> cardInfos() {
        return cards.stream()
                .map(Card::info)
                .toList();
    }

    List<Card> cards() {
        return List.copyOf(cards);
    }

    boolean isBusted() {
        int score = rawScoreSum();
        if (aceCount() > 0) {
            score -= aceCount() * BlackJackRule.ACE_WEIGHT.value();
        }
        return score > BlackJackRule.BUST_NUMBER.value();
    }

    int scoreSum() {
        int total = rawScoreSum();
        if (isExceededBustNumber(total)) {
            total -= aceCount() * BlackJackRule.ACE_WEIGHT.value();
        }
        return total;
    }

    private int rawScoreSum() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private boolean isExceededBustNumber(int total) {
        return total > BlackJackRule.BUST_NUMBER.value();
    }
}
