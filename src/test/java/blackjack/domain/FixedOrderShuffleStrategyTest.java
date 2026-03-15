package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class FixedOrderShuffleStrategyTest {

    @Test
    void 지정한_카드_순서대로_덱이_구성된다() {
        // draw()는 removeLast()이므로, 첫 번째로 뽑히길 원하는 카드를 마지막에 배치
        List<Card> fixedOrder = List.of(
                new Card(CardPoint.TWO, CardPattern.CLUB),
                new Card(CardPoint.ACE, CardPattern.HEART)  // ← 첫 번째로 뽑힘
        );

        Deck deck = new Deck(new FixedOrderShuffleStrategy(fixedOrder));

        assertThat(deck.draw().getName()).isEqualTo("A하트");
        assertThat(deck.draw().getName()).isEqualTo("2클로버");
    }
}
