package domain.player;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("카드 정상적으로 받기 테스트")
    void 카드_정상적으로_받기_테스트() {
        // given
        Card card = new Card("2", "하트");
        Player player = new Player("coco");

        // when
        player.addCard(card);

        // then
        Assertions.assertThat(player.getCardSize()).isEqualTo(1);
    }


    // 버스트 확인


    // 합산
}