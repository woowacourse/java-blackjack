package model.casino;

import java.util.Queue;
import model.card.Card;

public class CardDispenser {
    private final Queue<Card> blackJackCards;

    public CardDispenser(CardShuffleMachine cardShuffleMachine) {
        this.blackJackCards = cardShuffleMachine.shuffleCardDeck();
    }

    public Card dispenseCard(){
        if (blackJackCards.isEmpty()) {
            throw new IllegalStateException("Number of attempts exceeded");
        }
        return blackJackCards.poll();
    }

}
