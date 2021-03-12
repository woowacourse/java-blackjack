package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @DisplayName("카드 생성 테스트")
    @Test
    void createCard() {
        String shape = "다이아몬드";
        String number = "A";
        Card card = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);
        assertThat(card.toString()).isEqualTo(number + shape);
    }

    @DisplayName("카드 번호 반환 테스트")
    @Test
    void getCardNumber() {
        Card card = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);
        assertThat(card.getValue()).isEqualTo(CardNumberType.ACE.getValue());
    }

    @DisplayName("현재 점수 총 합에 따라 A 값 결정 : 총 합이 10이하일 때, A값은 11")
    @Test
    void aceValue11WhenScoreSumTenOrLess() {
        Card card = new Card(CardShapeType.CLUB, CardNumberType.ACE);
        assertThat(card.getBestValue(10)).isEqualTo(11);
    }

    @DisplayName("현재 점수 총 합에 따라 A 값 결정 : 총 합이 11이상일 때, A값은 1")
    @Test
    void aceValue1WhenScoreSum11OrMore() {
        Card card = new Card(CardShapeType.CLUB, CardNumberType.ACE);
        assertThat(card.getBestValue(11)).isEqualTo(1);
    }

    @DisplayName("A 이외의 카드는 모두 변화없이 자기자신의 값을 그대로 반환한다.")
    @Test
    void notAceGetValue() {
        for (CardNumberType cardNumberType : CardNumberType.values()) {
            if (cardNumberType != CardNumberType.ACE) {
                Card card = new Card(CardShapeType.CLUB, cardNumberType);
                assertThat(card.getBestValue(10)).isEqualTo(card.getValue());
                assertThat(card.getBestValue(11)).isEqualTo(card.getValue());
            }
        }
    }
}
