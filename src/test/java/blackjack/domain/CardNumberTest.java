package blackjack.domain;

import blackjack.domain.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardNumberTest {

    @DisplayName("모든 카드의 합계를 확인한다.")
    @Test
    void total() {
        List<CardNumber> cardNumbers = new ArrayList<>(List.of(CardNumber.values()));
        int total = CardNumber.getTotal(cardNumbers);

        assertThat(total).isEqualTo(85);
    }

    @DisplayName("ACE 가 1로 사용될 경우 합계를 확인한다.")
    @Test
    void total_ace_1() {
        List<CardNumber> cardNumbers = new ArrayList<>(List.of(CardNumber.ACE, CardNumber.KING, CardNumber.QUEEN));
        int total = CardNumber.getTotal(cardNumbers);

        assertThat(total).isEqualTo(21);
    }

    @DisplayName("ACE 가 11로 사용될 경우 합계를 확인한다.")
    @Test
    void total_ace_11() {
        List<CardNumber> cardNumbers = new ArrayList<>(List.of(CardNumber.ACE, CardNumber.QUEEN));
        int total = CardNumber.getTotal(cardNumbers);

        assertThat(total).isEqualTo(21);
    }

    @DisplayName("ACE 가 2개일 경우 합계를 확인한다.")
    @Test
    void total_two_ace() {
        List<CardNumber> cardNumbers = new ArrayList<>(List.of(CardNumber.ACE, CardNumber.ACE));
        int total = CardNumber.getTotal(cardNumbers);

        assertThat(total).isEqualTo(12);
    }

    @DisplayName("나머지 카드 합계가 11 이며 ACE 가 있을 경우 합계를 확인한다.")
    @Test
    void total_11_and_ace() {
        List<CardNumber> cardNumbers = new ArrayList<>(List.of(CardNumber.TWO, CardNumber.NINE, CardNumber.ACE));
        int total = CardNumber.getTotal(cardNumbers);

        assertThat(total).isEqualTo(12);
    }
}
