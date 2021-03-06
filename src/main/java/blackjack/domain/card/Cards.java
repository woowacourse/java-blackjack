package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.card.painting.Symbol.ACE;

public class Cards {
    private static final int BLACK_JACK = 21;
    private static final int ACE_ADDITIONAL_SCORE = 10;

    private final ArrayList<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card){
        cards.add(card);
    }

    public int size(){
        return cards.size();
    }

    public int calculateScore() {
        int score = sumScore();

        if (hasAce() && canAddAceScore(score)) {
            score += ACE_ADDITIONAL_SCORE;
        }

        return score;
    }

    private boolean canAddAceScore(int score) {
        return score + ACE_ADDITIONAL_SCORE <= BLACK_JACK;
    }

    private int sumScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(symbol -> symbol.isSameSymbol(ACE));
    }

    public List<Card> getCards(){
        return Collections.unmodifiableList(cards);
    }
}
