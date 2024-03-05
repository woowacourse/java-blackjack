package model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.card.Card;

public abstract class Player implements NoticeStatus {
    protected final static int MAXIMUM_SUM = 21;

    protected final String name;
    protected List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCards(List<Card> card) {
        cards.addAll(card);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sumCardNumbers() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.minimumNumber();
        }
        List<Integer> differenceNumbers = filterNonZeroDifferences();
        return differenceNumbers.stream()
                .reduce(sum, Player::sumIfUnderMaximum);
    }

    private List<Integer> filterNonZeroDifferences() {
        return cards.stream()
                .map(Card::subtractMaxMinNumber)
                .filter(subtractNumber -> subtractNumber != 0)
                .toList();

    }

    private static int sumIfUnderMaximum(Integer result, Integer number) {
        if (result + number <= 21) {
            return result + number;
        }
        return result;
    }

    public abstract boolean isDealer();
}
