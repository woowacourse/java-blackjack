package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.util.Lists.newArrayList;

class TrumpTest {

    @Test
    @DisplayName("트럼프 기본 카드 생성 테스트")
    void generateTrumpSuccess() {
        TestNumberGenerator testNumberGenerator = new TestNumberGenerator(newArrayList(3, 51, 30));

        assertThatCode(() -> new Trump(testNumberGenerator)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("올바른 인덱스에 있는 카드가 반환되는지 확인")
    void checkRightCardReturned() {
        TestNumberGenerator testNumberGenerator = new TestNumberGenerator(newArrayList(3, 50, 30));
        Trump trump = new Trump(testNumberGenerator);

        Card card1 = trump.getCard();
        Assertions.assertThat(card1.getTrumpShape()).isEqualTo(TrumpShape.SPADE);
        Assertions.assertThat(card1.getTrumpNumber()).isEqualTo(TrumpNumber.FOUR);
        Card card2 = trump.getCard();
        Assertions.assertThat(card2.getTrumpShape()).isEqualTo(TrumpShape.CLOVER);
        Assertions.assertThat(card2.getTrumpNumber()).isEqualTo(TrumpNumber.KING);
        Card card3 = trump.getCard();
        Assertions.assertThat(card3.getTrumpShape()).isEqualTo(TrumpShape.HEART);
        Assertions.assertThat(card3.getTrumpNumber()).isEqualTo(TrumpNumber.SIX);
    }
}
