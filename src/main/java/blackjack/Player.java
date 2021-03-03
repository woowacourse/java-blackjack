package blackjack;

import blackjack.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    public static final int BLACKJACK_NUMBER = 21;
    public static final int ZERO = 0;
    public static final int DIFFERENCE_OF_ACE_VALUE = 10;
    private final Name name;
    private final List<Card> cards = new ArrayList<>();

    public Player(String inputName) {
        this(new Name(inputName));
    }

    private Player(Name name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateResult() {
        int currentCardsValue = calculateCards();
        int possibleLoopCount = countAce();

        while(canLowerCardsValue(currentCardsValue, possibleLoopCount)) {
            currentCardsValue = lowerValueOfAce(currentCardsValue);
            possibleLoopCount--;
        }

        return currentCardsValue;
    }

    private boolean canLowerCardsValue(int value, int remainLoop) {
        return value > BLACKJACK_NUMBER && remainLoop > ZERO;
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

    public String getName() {
        return name.toString();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}