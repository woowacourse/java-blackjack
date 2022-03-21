package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DenominationTest {

    @DisplayName("블랙잭인지 확인한다.")
    @Test
    void is_blackjack_true() {
        List<Denomination> denominations = new ArrayList<>(List.of(Denomination.ACE, Denomination.QUEEN));

        assertThat(Denomination.isBlackjack(denominations)).isTrue();
    }

    @DisplayName("블랙잭이 아닌 것을 확인한다.")
    @Test
    void is_blackjack_false() {
        List<Denomination> denominations = new ArrayList<>(List.of(Denomination.ACE, Denomination.QUEEN, Denomination.QUEEN));

        assertThat(Denomination.isBlackjack(denominations)).isFalse();
    }

    @DisplayName("모든 카드의 합계를 확인한다.")
    @Test
    void total() {
        List<Denomination> denominations = new ArrayList<>(List.of(Denomination.values()));
        int total = Denomination.getTotal(denominations);

        assertThat(total).isEqualTo(85);
    }

    @DisplayName("ACE 가 없는 경우 합계를 확인한다.")
    @Test
    void total_not_contains_ace() {
        int total = Denomination.getTotal(List.of(Denomination.KING, Denomination.QUEEN));

        assertThat(total).isEqualTo(20);
    }

    @DisplayName("ACE 가 1로 사용될 경우 합계를 확인한다.")
    @Test
    void total_ace_1() {
        int total = Denomination.getTotal(List.of(Denomination.ACE, Denomination.KING, Denomination.QUEEN));

        assertThat(total).isEqualTo(21);
    }

    @DisplayName("ACE 가 11로 사용될 경우 합계를 확인한다.")
    @Test
    void total_ace_11() {
        int total = Denomination.getTotal(List.of(Denomination.ACE, Denomination.QUEEN));

        assertThat(total).isEqualTo(21);
    }

    @DisplayName("ACE 가 2개일 경우 합계를 확인한다.")
    @Test
    void total_two_ace() {
        int total = Denomination.getTotal(List.of(Denomination.ACE, Denomination.ACE));

        assertThat(total).isEqualTo(12);
    }

    @DisplayName("나머지 카드 합계가 11 이며 ACE 가 있을 경우 합계를 확인한다.")
    @Test
    void total_11_and_ace() {
        int total = Denomination.getTotal(List.of(Denomination.TWO, Denomination.NINE, Denomination.ACE));

        assertThat(total).isEqualTo(12);
    }
}
