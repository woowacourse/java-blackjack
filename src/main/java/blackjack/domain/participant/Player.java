package blackjack.domain.participant;

import static blackjack.domain.card.Rank.ACE_VALUE;
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
    public int getHandTotal() {
        // 먼저 모든 에이스를 11로 해서 더해보고 버스트면 에이스하나를 1로, 안되면 또 하나를 1로 해서
        // 버스트하지 않으면서 가장 큰 수 구해서 반환
        int total = cardHand.sumTotalExceptAce();
        int aceEleven = cardHand.getCountOfAce();
        int aceOne = 0;

        int result = total;
        if (aceEleven == 0) {
            return total;
        }

        while (0 <= aceEleven) {
            result = (aceEleven * ACE_VALUE) + aceOne + total;

            if (result <= TWENTY_ONE) {
                return result;
            }

            aceEleven--;
            aceOne++;
        }

        return result;
    }
}
