package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    private final CardHand cardHand;
    private final String name;

    private Player(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public static Player from(String name) {
        return new Player(name, new CardHand(new ArrayList<>()));
    }

    public boolean lowerThanThreshold() {
        return cardHand.lowerThanThreshold();
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public List<Card> getCardHand() {
        return cardHand.getCards();
    }

    public String getName() {
        return name;
    }

    //### 플레이어
//- [ ] 이름을 가짐
//- [ ] 카드 패를 가짐
//- [ ] 시작하면 카드 두 장을 받는다
//- [ ] 자신의 턴 동안 원하는 만큼 카드를 계속 뽑을 수 있다
//- [ ] 카드의 합이 21 이상이 되면 더 이상 뽑을 수 없다

}
