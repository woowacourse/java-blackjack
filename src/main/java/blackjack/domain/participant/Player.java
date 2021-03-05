package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Player extends Participant {

    private Player(String name, Hand cardHand) {
        super(name, cardHand);
    }

    public static Player from(String name) {
        return new Player(name, Hand.createEmptyHand());
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getCardSum() {
        //TODO 에이스
        // 카드 뽑을 때
        // 11로 계산해서 21이면
        return cardHand.playerSum();
    }
}
