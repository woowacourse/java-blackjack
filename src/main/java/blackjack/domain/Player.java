package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Player(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getMaxScore)
                .sum();

        //TODO 인덴트 해결하기
        for (Card card : cards) {
            if (score <= BLACKJACK_SCORE) {
                return score;
            }
            score = score + card.getMinScore() - card.getMaxScore();
        }
        return score;
    }
}
