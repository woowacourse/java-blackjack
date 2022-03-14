package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class Participant {

    protected static final String JOINING_DELIMITER = ", ";
    protected static final int BLACK_JACK_NUMBER = 21;
    protected static final int ACE_COUNT_LOWER_BOUND = 0;
    protected static final int ADDITIONAL_SCORE_OF_ACE = 10;

    private final Name name;
    private List<Card> hand;
    private final boolean isBlackJack;

    public Participant(Name name, List<Card> hand) {
        this.name = name;
        this.hand = new ArrayList<>(hand);
        this.isBlackJack = isMaxScore();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public String showHand() {
        return String.join(
                JOINING_DELIMITER,
                hand.stream().map(Card::toString).collect(Collectors.toList())
        );
    }

    public boolean isBust() {
        return getMinScore() > BLACK_JACK_NUMBER;
    }

    public boolean isMaxScore() {
        return getBestScore() == BLACK_JACK_NUMBER;
    }

    public int getBestScore() {
        int aceCount = getAceCount();
        int bestScore = getMinScore();
        while (aceCount > ACE_COUNT_LOWER_BOUND && bestScore + ADDITIONAL_SCORE_OF_ACE <= BLACK_JACK_NUMBER) {
            bestScore += ADDITIONAL_SCORE_OF_ACE;
            aceCount--;
        }
        return bestScore;
    }

    private int getMinScore() {
        return hand.stream().mapToInt(Card::getPoint).sum();
    }

    private int getAceCount() {
        return (int) hand.stream().filter(Card::isAce).count();
    }

    public Name getName() {
        return name;
    }

    protected List<Card> getHand() {
        return hand;
    }

    public boolean isBlackJack() {
        return isBlackJack;
    }
}
