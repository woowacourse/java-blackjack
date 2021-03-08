package blackjack.domain.card.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTypeTest {
    @DisplayName("현재 점수 총 합에 따라 A 값 결정 : 총 합이 10이하일 때, A값은 11")
    @Test
    void aceValue11WhenScoreSumTenOrLess() {
        CardNumberType aceType = CardNumberType.ACE;
        assertThat(aceType.getValue(aceType, 10)).isEqualTo(11);
    }

    @DisplayName("현재 점수 총 합에 따라 A 값 결정 : 총 합이 11이상일 때, A값은 1")
    @Test
    void aceValue1WhenScoreSum11OrMore() {
        CardNumberType aceType = CardNumberType.ACE;
        assertThat(aceType.getValue(aceType, 11)).isEqualTo(1);
    }

    @DisplayName("A 이외의 카드는 모두 변화없이 자기자신의 값을 그대로 반환한다.")
    @Test
    void notAceGetValue() {
        for (CardNumberType cardNumberType : CardNumberType.values()) {
            if (cardNumberType != CardNumberType.ACE) {
                assertThat(cardNumberType.getValue(cardNumberType, 10))
                    .isEqualTo(cardNumberType.getValue());
                assertThat(cardNumberType.getValue(cardNumberType, 11))
                    .isEqualTo(cardNumberType.getValue());
            }
        }
    }
}