package blackjackTest.service;

import blackjack.domain.Card;
import blackjack.domain.Player;
import blackjack.domain.Rank;
import blackjack.domain.Shape;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackTest {

    @Test
    void 플레이어_초기카드_합_21인지_확인() {
        Player pobi = new Player("pobi");
        pobi.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));

        assertThat(pobi.isBlackjack()).isEqualTo(true);
    }
}
