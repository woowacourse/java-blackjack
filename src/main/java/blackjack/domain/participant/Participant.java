package blackjack.domain.participant;

import blackjack.domain.Game;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    public static final int ZERO = 0;
    public static final int DIFFERENCE_OF_ACE_VALUE = 10;

    private final List<Card> cards = new ArrayList<>();
    protected GameResult gameResult = new GameResult();

    public void addCard() {
        cards.add(Deck.draw());
    }

    public int calculateResult() {
        int currentCardsValue = calculateCards();
        int possibleLoopCount = countAce();

        while (canLowerCardsValue(currentCardsValue, possibleLoopCount)) {
            currentCardsValue = lowerValueOfAce(currentCardsValue);
            possibleLoopCount--;
        }

        return currentCardsValue;
    }

    private boolean canLowerCardsValue(int value, int remainLoop) {
        return value > Game.BLACKJACK_NUMBER && remainLoop > ZERO;
    }

    private int lowerValueOfAce(int value) {
        return value - DIFFERENCE_OF_ACE_VALUE;
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int calculateCards() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public boolean isBurst() {
        return calculateResult() > Game.BLACKJACK_NUMBER;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }


    public int getWinCount() {
        return gameResult.getWinCount();
    }

    public int getDrawCount() {
        return gameResult.getDrawCount();
    }

    public int getLoseCount() {
        return gameResult.getLoseCount();
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public abstract String getName();
}


