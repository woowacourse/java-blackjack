package blackjack.domain.participant;

import static blackjack.domain.participant.Dealer.TWENTY_ONE;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Player extends Participant {

    public Player(String name, Hand cardHand) {
        super(name, cardHand);
    }

    public static Player from(String name) {
        return new Player(name, Hand.createEmptyHand());
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    @Override
    public boolean isBust() {
        return cardHand.sumAceToOne() > TWENTY_ONE;
    }

    @Override
    public int getHandTotal() {
        // todo ACE 두장 => 12 반환 필요 (현재는 2반환)
        int resultByOne = cardHand.sumAceToOne();
        int resultByEleven = cardHand.sumAceToEleven();

        // 21에 딱 맞음
        if (resultByOne == TWENTY_ONE) {
            return resultByOne;
        }
        if (resultByEleven == TWENTY_ONE) {
            return resultByEleven;
        }

        // 둘다 21을 넘지 않는다면 큰 수 리턴
        if (resultByEleven < TWENTY_ONE && resultByOne < TWENTY_ONE) {
            return Math.max(resultByEleven, resultByOne);
        }

        // 하나 or 둘이 21 넘음
        return Math.min(resultByEleven, resultByOne);
    }
}
