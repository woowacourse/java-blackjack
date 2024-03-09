package blackjack.domain.participants;


import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.Hands;
import java.util.ArrayList;

public class Player {

    private final Name name;
    private final Hands hands;

    public Player(Name name) {
        this.name = name;
        this.hands = new Hands(new ArrayList<>());
    }

    public void receiveDeck(Deck tempDeck) { // TODO 삭제
        int tempDeckSize = tempDeck.size();
        for (int i = 0; i < tempDeckSize; i++) {

        }
    }

    public void receiveCard(Card card) {
        hands.addCard(card);
    }

    public int calculateScore() {
        return 0;
    }

    public boolean isNotOver(int boundaryScore) {
        return 0 < boundaryScore;
    }

    public Name getName() {
        return name;
    }

    public Deck getDeck() { // TODO 삭제
        return null;
    }
}
