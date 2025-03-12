package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public List<Integer> getPossibleSums() {
        return hand.calculatePossibleSums();
    }

    public List<Card> getAllCards() {
        return hand.getAllCards();
    }

    public void takeCard(Card newCard) {
        hand.takeCard(newCard);
    }

    public Hand getCardHolder() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
