package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandStatusTest {

    @Test
    @DisplayName("카드 목록이 BlackJack인 경우는 총 스코어가 21이면서 카드의 수가 2장인 경우이다.")
    void getHandStatusWhenBlackJack() {
        int score = 21;
        int cardSize = 2;
        assertThat(HandStatus.of(score, cardSize)).isEqualTo(HandStatus.BLACKJACK);
    }
}
