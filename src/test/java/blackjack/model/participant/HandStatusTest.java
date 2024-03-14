package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandStatusTest {

    @Test
    @DisplayName("카드 목록이 Bust인 경우는 총 스코어가 21을 초과한 경우다.")
    void getHandStatusWhenBust() {
        int bustScore = 22;
        int cardSize = 3;
        assertThat(HandStatus.of(bustScore, cardSize)).isEqualTo(HandStatus.BUST);
    }

    @Test
    @DisplayName("카드 목록이 BlackJack인 경우는 총 스코어가 21이면서 카드의 수가 2장인 경우이다.")
    void getHandStatusWhenBlackJack() {
        int blackJackScore = 21;
        int blackJackCardSize = 2;
        assertThat(HandStatus.of(blackJackScore, blackJackCardSize)).isEqualTo(HandStatus.BLACKJACK);
    }

    @Test
    @DisplayName("카드 목록이 NotBlackJackBut21인 경우는 총 스코어가 21이지만 카드의 수가 2장이 아닌 경우이다.")
    void getHandStatusWhenNotBlackJackBut21() {
        int blackJackScore = 21;
        int notBlackJackCardSize = 3;
        assertThat(HandStatus.of(blackJackScore, notBlackJackCardSize)).isEqualTo(HandStatus.NOT_BLACKJACK_BUT_21);
    }

    @Test
    @DisplayName("카드 목록이 Under21인 경우는 총 스코어가 21 미만인 경우이다.")
    void getHandStatusWhenUnder21() {
        int under21Score = 20;
        int cardSize = 3;
        assertThat(HandStatus.of(under21Score, cardSize)).isEqualTo(HandStatus.UNDER_21);
    }
}
