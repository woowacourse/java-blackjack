package blackjack.domain.card;

import blackjack.exception.BlackJackException;
import java.util.ArrayDeque;
import java.util.Deque;

public class CardDeck {

    public static final String EXTERMINATION_MESSAGE = "[ERROR] 카드를 모두 소진했습니다.";
    private final Deque<Card> deck = new ArrayDeque<>(Card.getAllCard());

    public CardDeck() {
    }

    public synchronized UserDeck generateInitialUserDeck() {
        UserDeck userDeck = new UserDeck();
        userDeck.add(this.draw());
        userDeck.add(this.draw());
        return userDeck;
    }

    public synchronized Card draw() {
        if (deck.isEmpty()) {
            throw new BlackJackException(EXTERMINATION_MESSAGE);
        }
        return deck.removeFirst();
    }
}
