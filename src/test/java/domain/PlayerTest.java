package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}