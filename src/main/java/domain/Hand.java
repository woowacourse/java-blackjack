package domain;

import domain.constant.BlackJackRule;
import domain.vo.Card;
import domain.vo.CardInfo;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final DrawStrategy drawStrategy;
    private final List<Card> cards;

    Hand(DrawStrategy drawStrategy, List<Card> cards) {
        this.drawStrategy = drawStrategy;
        this.cards = new ArrayList<>(cards);
    }

    static Hand based(DrawStrategy drawStrategy) {
        return new Hand(drawStrategy, new ArrayList<>());
    }

    void drawCard() {
        cards.add(drawStrategy.draw());
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

    int scoreSum() {
        int total = rawScoreSum();
        if (isExceededBustNumber()) {
            total -= aceCount() * BlackJackRule.ACE_WEIGHT.value();
        }
        return total;
    }

    private boolean isExceededBustNumber() {
        return rawScoreSum() > BlackJackRule.BUST_NUMBER.value();
    }
}
