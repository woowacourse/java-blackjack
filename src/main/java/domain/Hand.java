package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

    private static final int BUST_NUMBER = 21;

    private final DrawStrategy drawStrategy;
    private final List<Card> cards;

    public Hand(DrawStrategy drawStrategy, List<Card> cards) {
        this.drawStrategy = drawStrategy;
        this.cards = new ArrayList<>(cards);
    }

    void drawCard() {
        cards.add(createCard());
    }

    List<String> cardInfos() {
        return cards.stream().map(Card::info).collect(Collectors.toList());
    }

    private Card createCard() {
        return drawStrategy.draw();
    }

    List<Card> cards() {
        return List.copyOf(cards);
    }

    int scoreSum() {
        int total = getSum();
        if(isBusted()) {
            total -= aceCount() * 10;
        }
        return total;
    }

    private int getSum() {
        return cards.stream().mapToInt(Card::score).sum();
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(c -> c.rank() == CardRank.ACE)
                .count();
    }

    public boolean isBusted() {
        return getSum() >= BUST_NUMBER;
    }
}
