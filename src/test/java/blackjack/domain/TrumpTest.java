package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrumpTest {

    @Test
    @DisplayName("트럼프 기본 카드 생성 테스트")
    void generateTrumpSuccess() {
        Trump trump = new Trump();

        Assertions.assertThat(trump.getCards().size()).isEqualTo(52);
    }
}
