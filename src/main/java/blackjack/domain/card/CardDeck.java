package blackjack.domain.card;

import blackjack.exception.BlackJackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CardDeck {

    public static final String EXTERMINATION_MESSAGE = "[ERROR] 카드를 모두 소진했습니다.";
    private final Deque<Card> deck;

    public CardDeck() {
        List<Card> cardBunch = new ArrayList<Card>();
        generateCardWithSymbol(cardBunch);
        Collections.shuffle(cardBunch);
        deck = new LinkedList<>(cardBunch);
    }

    private void generateCardWithSymbol(List<Card> cardBunch) {
        for (SymbolCandidate symbolCandidate : SymbolCandidate.values()) {
            CardSymbol cardSymbol = CardSymbol.from(symbolCandidate.getSymbol());
            generateCardWithNumber(cardBunch, cardSymbol);
        }
    }

    private void generateCardWithNumber(List<Card> cardBunch, CardSymbol cardSymbol) {
        for (NumberCandidate numberCandidate : NumberCandidate.values()) {
            CardNumber cardNumber = CardNumber.from(numberCandidate.getNumber());
            cardBunch.add(new Card(cardNumber, cardSymbol));
        }
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
