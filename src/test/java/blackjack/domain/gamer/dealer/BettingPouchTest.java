package blackjack.domain.gamer.dealer;

import blackjack.domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("배팅 파우치")
class BettingPouchTest {

    @Test
    @DisplayName("에서 플레이어의 자금을 저장한다.")
    void put() {
        // given
        BettingPouch bettingPouch = new BettingPouch();
        Money money1 = Money.from("5000");
        Money money2 = Money.from("7000");

        // when
        bettingPouch.put("seyang", money1);
        bettingPouch.put("lemone", money2);

        // then
        assertThat(bettingPouch.get())
                .containsExactlyEntriesOf(Map.of("seyang", Money.from("5000"), "lemone", Money.from("7000")));
    }
}
