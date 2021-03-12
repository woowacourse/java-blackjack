package blackjack.domain.card;

import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int ZERO = 0;
    private static final int DIFFERENCE_OF_ACE_VALUE = 10;
    private static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_CONDITION_CARDS_SIZE = 2;
    private final List<Card> cards = new ArrayList<>();

    public Cards(Card firstCard, Card secondCard) {
        cards.add(firstCard);
        cards.add(secondCard);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CONDITION_CARDS_SIZE && calculateCardsScore() == BLACKJACK_NUMBER;
    }

    public int calculateCardsScore() {
        int currentCardsScore = sumCardsScore();
        int possibleLoopCount = countAce();

        while (canLowerCardsValue(currentCardsScore, possibleLoopCount)) {
            currentCardsScore = lowerValueOfAce(currentCardsScore);
            possibleLoopCount--;
        }

        return currentCardsScore;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int sumCardsScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean canLowerCardsValue(int score, int remainLoop) {
        return score > BLACKJACK_NUMBER && remainLoop > ZERO;
    }

    private int lowerValueOfAce(int value) {
        return value - DIFFERENCE_OF_ACE_VALUE;
    }

    public String getCardsInfoToString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(OutputView.DELIMITER));
    }

    public String getFirstCardInfoToString() {
        return cards.get(0).toString();
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}