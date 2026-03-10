package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 플레이어가_카드를_받는다() {
        Player player = new Player("모카");
        player.recieveCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        assertThat(player.getCardCount()).isEqualTo(1);
    }

    @Test
    void 플레이어_버스트로_카드를_받을_수_없다() {
        Player player = new Player("이산");
        player.recieveCard(new Card(CardPoint.KING, CardPattern.DIAMOND));
        player.recieveCard(new Card(CardPoint.EIGHT, CardPattern.CLUB));
        player.recieveCard(new Card(CardPoint.FOUR, CardPattern.HEART));
        player.recieveCard(new Card(CardPoint.THREE, CardPattern.SPADE));
        assertThat(player.getCardCount()).isEqualTo(3);
    }

}
