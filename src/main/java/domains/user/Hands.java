package domains.user;

import domains.card.Card;
import domains.card.Deck;

import java.util.ArrayList;
import java.util.List;

public class Hands {
    public static final int ACE_SCORE_CHANGE_POINT = 11;
    public static final int ACE_EXTRA_SCORE = 10;
    public static final int BURST_SCORE = 21;

    private List<Card> hands;

    public Hands(List<Card> hands) {
        this.hands = hands;
    }

    public Hands(Deck deck) {
        this.hands = deck.initialDraw();

    }

    public int size() {
        return hands.size();
    }

    public void draw(Deck deck) {
        hands.add(deck.draw());
    }

    public int score() {
        int score = 0;
        boolean hasAce = false;
        for (Card hand : hands) {
            hasAce = checkAce(hand, hasAce);
            score += hand.score();
        }
        score = determineAceScore(score, hasAce);
        return score;
    }

    private int determineAceScore(int score, boolean hasAce) {
        if (score <= ACE_SCORE_CHANGE_POINT && hasAce) {
            return score + ACE_EXTRA_SCORE;
        }
        return score;
    }

    private boolean checkAce(Card card, boolean hasAce) {
        return hasAce || card.isAce();
    }

    public boolean isBurst() {
        return score() > BURST_SCORE;
    }

    public boolean isBlackJack() {
        return score() == BURST_SCORE;
    }

    public Card from(int index) {
        return hands.get(index);
    }

    @Override
    public String toString() {
        List<String> cards = new ArrayList<>();
        for (Card card : hands) {
            cards.add(card.toString());
        }
        return String.join(",", cards);
    }
}
