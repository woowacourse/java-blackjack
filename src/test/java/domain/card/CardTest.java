package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("블랙잭 게임의 ")
public class CardTest {
    @DisplayName("하트 카드는 문양 당 13장을 생성한다.")
    @Test
    void generateHeartCardTest() {
        assertThat(HeartCard.values().length).isEqualTo(13);
    }

    @DisplayName("다이아몬드 카드는 문양 당 13장을 생성한다.")
    @Test
    void generateDiamondCardTest() {
        assertThat(DiamondCard.values().length).isEqualTo(13);
    }

    @DisplayName("클로버 카드는 문양 당 13장을 생성한다.")
    @Test
    void generateCloverCardTest() {
        assertThat(CloverCard.values().length).isEqualTo(13);
    }

    @DisplayName("스페이드 카드는 문양 당 13장을 생성한다.")
    @Test
    void generateSpadeCardTest() {
        assertThat(SpadeCard.values().length).isEqualTo(13);
    }
}
