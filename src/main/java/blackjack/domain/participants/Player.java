package blackjack.domain.participants;


import blackjack.domain.Cards.Card;
import blackjack.domain.Cards.Deck;
import blackjack.domain.Cards.Hand;

public class Player {
    private final Name name;
    private final Hand hand;

    public Player(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void receiveDeck(Deck tempDeck) {
        int tempDeckSize = tempDeck.size();
        for (int i = 0; i < tempDeckSize; i++) {
            hand.addCard(tempDeck.pickRandomCard());
        }
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int calculateScore() {
        return hand.calculateTotalScore();
    }

    public boolean isNotOver(int boundaryScore) {
        return hand.calculateTotalScore() < boundaryScore;
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
