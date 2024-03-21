package model.casino;

import java.util.Queue;
import model.card.Card;

public class CardDispenser {
    private final Queue<Card> blackJackCards;

    public CardDispenser(CardShuffleMachine cardShuffleMachine) {
        this.blackJackCards = cardShuffleMachine.shuffleCardDeck();
    }

    public Card dispenseCard() {
        if (blackJackCards.isEmpty()) {
            throw new IllegalStateException("호출 가능한 횟수를 초과하였습니다.");
        }
        return blackJackCards.poll();
    }

}
