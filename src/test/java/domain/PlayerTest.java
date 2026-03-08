package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    static Hand playingHand = new Hand(
            List.of(
                    new Card(CardRank.QUEEN, CardMark.SPADE),
                    new Card(CardRank.TWO, CardMark.HEART)));
    static Hand stopHand = new Hand(
            List.of(
                    new Card(CardRank.QUEEN, CardMark.SPADE),
                    new Card(CardRank.EIGHT, CardMark.HEART),
                    new Card(CardRank.THREE, CardMark.HEART)));

    @Test
    @DisplayName("플레이어는 패의 합이 20이하일 때 더 받을 수 있다.")
    void 플레이어_카드_뽑기_가능_테스트() {
        Player player = new Player("pobi", playingHand);

        assertTrue(player.isPlayable());
    }

    @Test
    @DisplayName("플레이어는 패의 합이 21이상일 때 더 받을 수 없다.")
    void 플레이어_카드_뽑기_불가능_테스트() {
        Player player = new Player("pobi", stopHand);

        assertFalse(player.isPlayable());
    }
}
