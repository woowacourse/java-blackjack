package domain.gameplaying;

import static org.junit.jupiter.api.Assertions.*;

import domain.gameplaying.strategy.RandomStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    static Hand playableHand = new Hand(
            new RandomStrategy(),
            List.of(new Card(CardRank.QUEEN, CardMark.SPADE),
                    new Card(CardRank.TWO, CardMark.HEART)));
    static Hand stopHand = new Hand(
            new RandomStrategy(),
            List.of(new Card(CardRank.QUEEN, CardMark.SPADE),
                    new Card(CardRank.EIGHT, CardMark.HEART)));

    @Test
    @DisplayName("딜러는 패의 합이 16이하일 때 더 받을 수 있다.")
    void 딜러_카드_뽑기_가능_테스트() {
        Dealer dealer = new Dealer("딜러", playableHand);

        assertTrue(dealer.isPlayable());
    }

    @Test
    @DisplayName("딜러는 패의 합이 17 이상일 때 더 받을 수 없다.")
    void 딜러_카드_뽑기_불가능_테스트() {
        Dealer dealer = new Dealer("딜러", stopHand);

        assertFalse(dealer.isPlayable());
    }
}
