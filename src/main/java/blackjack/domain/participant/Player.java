package blackjack.domain.participant;

import blackjack.domain.card.CardHand;

import java.util.ArrayList;

public class Player extends Participant {
    
    private static final int MAXIMUM_THRESHOLD = 21;
    
    private Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }
    
    public static Player from(String name) {
        return new Player(name, new CardHand(new ArrayList<>()));
    }
    
    public boolean canReceive() {
        return cardHand.sum() <= MAXIMUM_THRESHOLD;
    }
    //### 플레이어
    //- [ ] 이름을 가짐
    //- [ ] 카드 패를 가짐
    //- [ ] 시작하면 카드 두 장을 받는다
    //- [ ] 자신의 턴 동안 원하는 만큼 카드를 계속 뽑을 수 있다
    //- [ ] 카드의 합이 21 이상이 되면 더 이상 뽑을 수 없다
}
