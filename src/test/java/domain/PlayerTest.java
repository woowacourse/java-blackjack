package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        Player player = new Player("요크");
        this.player = player;
    }

    @DisplayName("카드 합계 구하기")
    @Test
    void 카드_합계_정상_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.THREE));
        player.add(new Card(CardShape.SPADE, CardRank.TWO));

        int result = player.calculateScore();

        assertThat(result).isEqualTo(5);
    }

    @DisplayName("ACE가 11이어야 함")
    @Test
    void 카드_합계_에이스_11_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.TWO));

        int result = player.calculateScore();
        assertThat(result).isEqualTo(13);
    }

    @DisplayName("ACE가 1이어야 함")
    @Test
    void 카드_합계_에이스_1_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.NINE));
        player.add(new Card(CardShape.SPADE, CardRank.TWO));

        int result = player.calculateScore();
        assertThat(result).isEqualTo(12);
    }

    @DisplayName("ACE가 하나는 11이고 하나는 1이어야 함")
    @Test
    void 카드_합계_에이스_11_1_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.NINE));

        int result = player.calculateScore();
        assertThat(result).isEqualTo(21);
    }

    @DisplayName("ACE가 둘 다 1이어야 함")
    @Test
    void 카드_합계_에이스_2개_1_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.ACE));
        player.add(new Card(CardShape.SPADE, CardRank.TEN));

        int result = player.calculateScore();
        assertThat(result).isEqualTo(12);
    }

    @DisplayName("Burst 여부 판단 - burst")
    @Test
    void Burst_여부_판단_burst_정상_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.TEN));
        player.add(new Card(CardShape.HEART, CardRank.TWO));
        player.add(new Card(CardShape.HEART, CardRank.TEN));

        boolean result = player.isBurst();
        assertThat(result).isEqualTo(true);
    }

    @DisplayName("Burst 여부 판단 - not Burst")
    @Test
    void Burst_여부_판단_not_burst_정상_테스트() {
        player.add(new Card(CardShape.SPADE, CardRank.TEN));
        player.add(new Card(CardShape.HEART, CardRank.TWO));

        boolean result = player.isBurst();
        assertThat(result).isEqualTo(false);
    }
}