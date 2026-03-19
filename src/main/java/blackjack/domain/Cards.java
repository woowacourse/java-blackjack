package blackjack.domain;

import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.State;
import blackjack.domain.state.Stay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    public static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_SIZE = 2;
    private static final int ACE_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public State getState() {
        if (isBust()) {
            return new Bust(sumScore());
        }
        if (isBlackjack()) {
            return new Blackjack();
        }
        return new Stay(sumScore());
    }

    public int sumScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScore();
        }
        int aceCount = (int) cards.stream().filter(Card::isAce).count();
        while (totalScore > BLACKJACK_SCORE && aceCount > 0) {
            totalScore -= ACE_SCORE;
            aceCount--;
        }
        return totalScore;
    }

    public boolean isBust() {
        return sumScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return sumScore() == BLACKJACK_SCORE && cards.size() == BLACKJACK_SIZE;
    }

    public void addCard(Card card) {
        if (card == null || sumScore() > BLACKJACK_SCORE) {
            throw new IllegalArgumentException("[ERROR] 카드를 추가할 수 없습니다.");
        }
        cards.add(card);
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toList());
    }

}