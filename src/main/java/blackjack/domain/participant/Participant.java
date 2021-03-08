package blackjack.domain.participant;

import blackjack.domain.Game;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private static final int ZERO = 0;
    private static final int DIFFERENCE_OF_ACE_VALUE = 10;

    protected final Name name;
    protected final List<Card> cards = new ArrayList<>();

    protected Participant(String inputName) {
        this.name = new Name(inputName);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateCardsScoreResult() {
        int currentCardsScore = calculateCardsScore();
        int possibleLoopCount = countAce();

        while (canLowerCardsValue(currentCardsScore, possibleLoopCount)) {
            currentCardsScore = lowerValueOfAce(currentCardsScore);
            possibleLoopCount--;
        }

        return currentCardsScore;
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateCardsScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean canLowerCardsValue(int score, int remainLoop) {
        return score > Game.BLACKJACK_NUMBER && remainLoop > ZERO;
    }

    public boolean isBurst() {
        return calculateCardsScoreResult() > Game.BLACKJACK_NUMBER;
    }

    private int lowerValueOfAce(int value) {
        return value - DIFFERENCE_OF_ACE_VALUE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract String getName();
}