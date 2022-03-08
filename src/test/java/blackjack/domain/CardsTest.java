package blackjack.domain;

import static blackjack.domain.CardNumber.TEN;
import static blackjack.domain.CardNumber.THREE;
import static blackjack.domain.CardNumber.TWO;
import static blackjack.domain.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드의 합의 반환한다.")
    void calculateScore() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, TWO), Card.of(SPADE, THREE), Card.of(SPADE, TEN)));
        assertThat(cards.calculateScore()).isEqualTo(15);
    }
    @Test
    @DisplayName("생성 시 카드에는 null이 들어올 경우 예외를 발생해야 한다.")
    void createNullException() {
        assertThatThrownBy(() -> new Cards(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[Error] 카드에는 null이 들어올 수 없습니다.");
    }
}