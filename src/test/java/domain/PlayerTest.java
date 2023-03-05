package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.user.Player;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class PlayerTest {

    @DisplayName("플레이어 테스트.")
    @TestFactory
    Stream<DynamicTest> getSizeDynamicTest() {
        Player player = new Player("split");
        Card firstCardTwoSpade = new Card(Denomination.TWO, Suit.SPADE);
        Card secondCardAceDiamond = new Card(Denomination.ACE, Suit.DIAMOND);
        Card thirdCardNineHeart = new Card(Denomination.NINE, Suit.HEART);
        return Stream.of(
            dynamicTest("이름과 플레이어 여부를 확인한다.", () -> {
                assertThat(player.getName()).isEqualTo("split");
                assertThat(player.isPlayer()).isTrue();
            }),
            dynamicTest("카드 1개를 지급한다.", () -> {
                player.dealt(firstCardTwoSpade);
                assertThat(player.showHand()).containsExactly(firstCardTwoSpade);
            }),
            dynamicTest("게임할 준비가 되지 않은 채 게임할 카드를 조회하면 오류를 던진다.", () -> {
                assertThatThrownBy(() -> player.faceUpInitialHand())
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessage("모든 플레이어는 카드 두장을 받고 시작해야 합니다.");
            }),
            dynamicTest("1개를 더 지급하여 준비완료하고 게임할 2장의 카드를 조회한다.", () -> {
                player.dealt(secondCardAceDiamond);
                assertThat(player.faceUpInitialHand())
                    .containsExactly(firstCardTwoSpade, secondCardAceDiamond);
            }),
            dynamicTest("가지고 있는 카드의 점수를 조회한다.(ACE가 11로 계산되면 유리한 경우)", () -> {
                assertThat(player.hasAce()).isTrue();
                assertThat(player.calculatePoint()).isEqualTo(13);
            }),
            dynamicTest("한장을 더 지급하고 가지고 있는 카드의 점수를 조회한다.(ACE가 11로 계산되면 불리한 경우)", () -> {
                player.dealt(thirdCardNineHeart);
                assertThat(player.hasAce()).isTrue();
                assertThat(player.calculatePoint()).isEqualTo(12);
            }),
            dynamicTest("가지고 있는 모드 카드를 조회한다.", () -> {
                List<Card> cards = player.showHand();
                assertThat(cards).hasSize(3);
                assertThat(cards).containsExactly(firstCardTwoSpade, secondCardAceDiamond, thirdCardNineHeart);
            })
        );
    }
}
