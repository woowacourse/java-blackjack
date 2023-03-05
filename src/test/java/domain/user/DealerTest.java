package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class DealerTest {

    @DisplayName("딜러 테스트.")
    @TestFactory
    Stream<DynamicTest> getSizeDynamicTest() {
        Dealer dealer = new Dealer();
        Card firstCardSixSpade = new Card(Denomination.ACE, Suit.SPADE);
        Card secondSixSpade = new Card(Denomination.SIX, Suit.SPADE);
        return Stream.of(
            dynamicTest("이름과 딜러 여부를 확인한다.", () -> {
                assertThat(dealer.getName()).isEqualTo("딜러");
                assertThat(dealer.isPlayer()).isFalse();
            }),
            dynamicTest("게임할 준비가 되지 않은 채 게임할 카드를 조회하면 오류를 던진다.", () -> {
                dealer.dealt(firstCardSixSpade);
                assertThatThrownBy(dealer::faceUpInitialHand)
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessage("딜러는 카드 두장을 받고 시작해야 합니다.");
            }),
            dynamicTest("준비가 완료되면 딜러는 1장의 카드를 오픈한다.", () -> {
                dealer.dealt(secondSixSpade);
                assertThat(dealer.faceUpInitialHand()).containsExactly(firstCardSixSpade);
            }),
            dynamicTest("가지고 있는 카드의 점수를 반환한다.(ACE가 11로 계산되어 17이상이 되면 11로 계산된다.)", () -> {
                assertThat(dealer.calculatePoint()).isEqualTo(17);
            })
        );
    }
}
