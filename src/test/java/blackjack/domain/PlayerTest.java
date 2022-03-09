package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Nested
    @DisplayName("drawCard는")
    class DrawCard {

        @Test
        @DisplayName("Card를 자신의 패에 추가한다.")
        void addCard() {
            Player player = new Player("roma");
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.J));
            Assertions.assertThat(player.getTotalNumber()).isEqualTo(10);
        }

        @Test
        @DisplayName("자신의 패의 합이 21을 초과하는 경우 예외를 발생시킨다.")
        void throwExceptionOver21() {
            Player player = new Player("roma");
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.J));
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.K));
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.TWO));

            Assertions.assertThatThrownBy(() -> player.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.ACE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("버스트여서 더 이상 드로우할 수 없습니다.");
        }
    }
}
