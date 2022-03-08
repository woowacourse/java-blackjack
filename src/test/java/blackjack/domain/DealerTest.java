package blackjack.domain;

import static blackjack.domain.CardNumber.A;
import static blackjack.domain.CardNumber.SEVEN;
import static blackjack.domain.CardNumber.TEN;
import static blackjack.domain.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 카드가 17이상일 때 카드를 추가하면 예외가 발생해야 한다.")
    void drawExceptionByLimitDealerScore() {
        final Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN))));
        assertThatThrownBy(() -> dealer.draw(Card.of(SPADE, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[Error] 딜러 카드가 이미 17이상입니다.");
    }
}