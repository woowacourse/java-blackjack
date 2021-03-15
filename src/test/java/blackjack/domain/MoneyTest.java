package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 생성_및_비교() {
        // given, when
        Money money1 = Money.of(100.0);
        Money money2 = Money.of(100.0);

        // then
        assertThat(money1).isEqualTo(money2);
    }

}