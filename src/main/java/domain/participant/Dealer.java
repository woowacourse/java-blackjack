package domain.participant;

import domain.card.Card;
import java.util.List;

/**
 * 딜러를 관리하는 클래스
 */
public class Dealer extends Participant {
    public static final int RECEIVE_CARD_CONDITION = 16;

    public Dealer(List<Card> hand) {
        super(hand);
    }

    public boolean canReceiveCard() {
        return calculateScore() <= RECEIVE_CARD_CONDITION;
    }
}
