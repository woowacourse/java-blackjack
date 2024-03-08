package domain.deck;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDeck {
    private static final int BLACK_JACK_CONDITION = 21;
    private static final int ACE_CONVERT_VALUE = 10;
    final List<Card> cards;

    public UserDeck() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sumCard() {
        int sum = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
        return addSumByAce(sum);
    }

    private int addSumByAce(int sum) {
        if (sum + ACE_CONVERT_VALUE <= BLACK_JACK_CONDITION && hasAce()) {
            sum += ACE_CONVERT_VALUE;
        }
        return sum;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean busted() {
        return sumCard() > BLACK_JACK_CONDITION;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
