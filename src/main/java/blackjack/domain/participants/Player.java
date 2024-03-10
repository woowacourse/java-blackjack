package blackjack.domain.participants;


import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.Hands;
import java.util.ArrayList;

public class Player {

    private final Name name;
    private Hands hands;

    public Player(Name name) {
        this.name = name;
    }

    public void receiveHands(Hands hands) {
        this.hands = hands;
    }

    public void receiveCard(Card card) {
        if (hands == null) {
            hands = new Hands(new ArrayList<>());
        }
        hands.addCard(card);
    }

    public int calculateScore() {
        return hands.calculateScore();
    }

    public boolean isNotOver(int boundaryScore) {
        return hands.calculateScore() < boundaryScore;
    }

    public boolean isWin(int dealerScore) {
        return hands.calculateScore() > dealerScore;
    }

    public Name getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }
}
