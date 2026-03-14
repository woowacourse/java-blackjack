package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
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

    public int getSize() {
        return cards.size();
    }
}