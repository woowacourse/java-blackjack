package domain.gameplaying;

import static org.junit.jupiter.api.Assertions.*;

import domain.gameplaying.strategy.InfiniteDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    static Hand playingHand = new Hand(
            new InfiniteDeck(),
            List.of(
                    new Card(CardRank.QUEEN, CardMark.SPADE),
                    new Card(CardRank.TWO, CardMark.HEART)));

    static Hand stopHand = new Hand(
            new InfiniteDeck(),
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

    @Test
    @DisplayName("플레이어의 이름이 \"딜러\"이면 예외를 발생시켜야 한다.")
    void player_name_exception_test() {
        assertThrows(IllegalArgumentException.class, () -> Player.of("딜러", new InfiniteDeck()));
    }
}
