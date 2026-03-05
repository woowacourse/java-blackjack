package domain;

import static org.junit.jupiter.api.Assertions.*;

import strategy.RandomStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    static Hand playingHand = new Hand(
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
        Dealer dealer = new Dealer("딜러", playingHand);

        assertTrue(dealer.isPlaying());
    }

    @Test
    @DisplayName("딜러는 패의 합이 17 이상일 때 더 받을 수 없다.")
    void 딜러_카드_뽑기_불가능_테스트() {
        Dealer dealer = new Dealer("딜러", stopHand);

        assertFalse(dealer.isPlaying());
    }

}