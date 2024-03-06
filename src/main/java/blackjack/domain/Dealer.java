package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private static final int DRAWABLE_MAX_SCORE = 16;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Dealer(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public boolean isDrawable() {
        return calculateScore() <= DRAWABLE_MAX_SCORE;
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

    public void playTurn(Deck deck) {
        while (isDrawable()) {
            cards.add(deck.draw());
        }
    }

    List<Card> getCards() {
        return cards;
    }
}
