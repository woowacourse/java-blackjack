package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public abstract class Participant {

    private static final String JOINING_DELIMITER = ", ";
    private static final int BLACK_JACK_NUMBER = 21;
    private static final int ACE_COUNT_LOWER_BOUND = 0;
    private static final int ADDITIONAL_SCORE_OF_ACE = 10;
    private static final int SECOND_INDEX_OF_HAND = 1;
    private static final int POINT_OF_ACE = 1;
    private static final int POINT_OF_TEN = 10;

    protected static final int FIRST_INDEX_OF_HAND = 0;

    private final Name name;
    private List<Card> hand;

    public Participant(Name name, List<Card> hand) {
        this.name = name;
        this.hand = new ArrayList<>(hand);
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public abstract boolean isNeedToDraw();

    public String showHand() {
        return String.join(
                JOINING_DELIMITER,
                hand.stream().map(Card::combineRankAndSuit).collect(Collectors.toList())
        );
    }

    public boolean isBust() {
        return calculateMinScoreOfHand() > BLACK_JACK_NUMBER;
    }

    public boolean isUpperBoundScore() {
        return calculateBestScore() == BLACK_JACK_NUMBER;
    }

    public int calculateBestScore() {
        int aceCount = countAceCard();
        int bestScore = calculateMinScoreOfHand();
        while (aceCount > ACE_COUNT_LOWER_BOUND && bestScore + ADDITIONAL_SCORE_OF_ACE <= BLACK_JACK_NUMBER) {
            bestScore += ADDITIONAL_SCORE_OF_ACE;
            aceCount--;
        }
        return bestScore;
    }

    private int countAceCard() {
        return (int) hand.stream().filter(Card::isAce).count();
    }

    private int calculateMinScoreOfHand() {
        return hand.stream().mapToInt(Card::getPoint).sum();
    }

    public boolean isBlackJack() {
        int firstCardPoint = hand.get(FIRST_INDEX_OF_HAND).getPoint();
        int secondCardPoint = hand.get(SECOND_INDEX_OF_HAND).getPoint();
        if (firstCardPoint == POINT_OF_ACE && secondCardPoint == POINT_OF_TEN) {
            return true;
        }
        if (firstCardPoint == POINT_OF_TEN && secondCardPoint == POINT_OF_ACE) {
            return true;
        }
        return false;
    }

    public Name getName() {
        return name;
    }

    protected List<Card> getHand() {
        return hand;
    }
}
