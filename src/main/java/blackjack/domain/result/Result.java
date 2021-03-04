package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;

public class Result {
    //딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
    //pobi 카드: 2하트, 8스페이드, A클로버 - 결과: 21
    //jason 카드: 7클로버, K스페이드 - 결과: 17

    //## 최종 승패
    //딜러: 1승 1패
    //pobi: 승
    //jason: 패
    Dealer dealer;
    List<Player> players;

    public List<Card> getDealerCardHand() {
        return dealer.getCardHand();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getDealerSum() {
        return dealer.getCardSum();
    }

    public String dealerName() {
        return dealer.getName();
    }
}
