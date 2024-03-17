package blackjack.view;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Denomination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputDenominationTest {

    @DisplayName("카드 값을 입력하면 출력용 카드 문자열을 반환한다")
    @Test
    public void convertDenominationToString() {
        assertAll(
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.ACE)).isEqualTo("A"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.TWO)).isEqualTo("2"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.THREE)).isEqualTo("3"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.FOUR)).isEqualTo("4"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.FIVE)).isEqualTo("5"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.SIX)).isEqualTo("6"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.SEVEN)).isEqualTo("7"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.EIGHT)).isEqualTo("8"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.NINE)).isEqualTo("9"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.TEN)).isEqualTo("10"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.JACK)).isEqualTo("J"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.QUEEN)).isEqualTo("Q"),
                () -> assertThat(OutputDenomination.convertDenominationToString(Denomination.KING)).isEqualTo("K")
        );
    }
}
