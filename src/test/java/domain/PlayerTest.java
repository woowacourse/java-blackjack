package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 카드 추가 확인 테스트")
    void 카드_추가_확인() {
        Player bustPlayer = PlayerFixture.createBust("pobi");
        Player defaultPlayer = PlayerFixture.createDefault("jason");

        bustPlayer.addHandCard(Card.of(CardNumber.FIVE, CardShape.DIAMOND));
        defaultPlayer.addHandCard(Card.of(CardNumber.FOUR, CardShape.DIAMOND));
        assertThat(bustPlayer.getHandCards().size()).isEqualTo(4);
        assertThat(defaultPlayer.getHandCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어의 버스트 여부 확인 테스트")
    void 버스트_여부_확인() {
        Player bustPlayer = PlayerFixture.createBust("pobi");
        Player defaultPlayer = PlayerFixture.createDefault("jason");

        assertThat(bustPlayer.isBust()).isTrue();
        assertThat(defaultPlayer.isBust()).isFalse();
    }
}