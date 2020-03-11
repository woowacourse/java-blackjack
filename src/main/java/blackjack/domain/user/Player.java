package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int BURSTED_THRESHOLD = 21;
    private static final int ACE_NUMBER_GAP = 10;

    private final List<Card> cards = new ArrayList<>();
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateSum() {
        int result = sumAll();
        return subtractIfContainingAce(result);
    }

    private int sumAll() {
        return cards.stream()
                    .mapToInt(Card::getNumber)
                    .sum();
    }

    private int subtractIfContainingAce(int result) {
        for (Card card : cards) {
            if (result > BURSTED_THRESHOLD && card.isAce()) {
                result -= ACE_NUMBER_GAP;
            }
        }
        return result;
    }

}
