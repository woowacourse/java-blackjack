package domain;

import common.Constants;
import domain.vo.CardInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

//    private final DrawStrategy drawStrategy;
    private final List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    static Hand empty () {
        return new Hand(new ArrayList<>());
    }

    void drawCard(DrawStrategy drawStrategy) {
        cards.add(drawStrategy.draw());
    }

    List<CardInfo> cardInfos() {
        return cards.stream().map(Card::info).collect(Collectors.toList());
    }

    List<Card> cards() {
        return List.copyOf(cards);
    }

    boolean isBusted() {
        int score = rawScoreSum();
        if (aceCount() > 0) {
            score -= aceCount() * Constants.ACE_WEIGHT;
        }
        return score > Constants.BUST_NUMBER;
    }

    int scoreSum() {
        int total = rawScoreSum();
        if (isOvercome()) {
            total -= aceCount() * Constants.ACE_WEIGHT;
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

    private boolean isOvercome() {
        return rawScoreSum() > Constants.BUST_NUMBER;
    }
}
