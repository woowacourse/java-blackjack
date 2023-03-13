package playerState;

import static testCards.TestCards.ACE_DIAMOND;
import static testCards.TestCards.QUEEN_DIAMOND;
import static testCards.TestCards.TEN_DIAMOND;
import static testCards.TestCards.THREE_DIAMOND;
import static testCards.TestCards.TWO_DIAMOND;

import card.Hand;
import gameState.BettingAmount;
import gameState.Draw;
import gameState.Lose;
import gameState.Playing;
import gameState.Win;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class StandTest {

    @DisplayName("Stand 할 수 없는 카드로 생성하면 오류를 던진다..")
    @Test
    void notStand() {
        Hand hand = new Hand(List.of(TWO_DIAMOND, TEN_DIAMOND, QUEEN_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Assertions.assertThatThrownBy(() -> Stand.of(playing, hand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Stand 할 수 있는 카드 상태가 아닙니다.");
    }

    @DisplayName("Stand에서 카드를 지급하면 오류를 던진다..")
    @Test
    void standCanNotAddCard() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, QUEEN_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Stand stand = Stand.of(playing, hand);
        Assertions.assertThatThrownBy(() -> stand.addCard(TWO_DIAMOND))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("더 이상 카드를 뽑을 수 없습니다.");
    }

    @DisplayName("플레이어가 Stand 일 때 딜러와 비교하여 결과를 저장한다.")
    @TestFactory
    Stream<DynamicTest> playerStand() {
        Hand bustHand = new Hand(List.of(TEN_DIAMOND, QUEEN_DIAMOND, TWO_DIAMOND));
        Hand standHand = new Hand(List.of(TWO_DIAMOND, QUEEN_DIAMOND));
        Hand blackJackHand = new Hand(List.of(TEN_DIAMOND, ACE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        PlayerStatus playerStatus = Stand.of(playing, standHand);
        return Stream.of(
            DynamicTest.dynamicTest("딜러가 Bust인 경우 이긴다", () -> {
                PlayerStatus dealerStatus = Bust.of(playing, bustHand);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Win.class);
            }),
            DynamicTest.dynamicTest("딜러가 BlackJack인 경우 진다", () -> {
                PlayerStatus dealerStatus = BlackJack.of(playing, blackJackHand);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Lose.class);
            }),
            DynamicTest.dynamicTest("딜러가 Stand고 점수가 더 높을 경우 진다", () -> {
                Hand threePointMore = standHand.addCard(THREE_DIAMOND);
                PlayerStatus dealerStatus = Stand.of(playing, threePointMore);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Lose.class);
            }),
            DynamicTest.dynamicTest("딜러가 Stand고 점수가 같을 경우 비긴다", () -> {
                PlayerStatus dealerStatus = Stand.of(playing, standHand);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Draw.class);
            }),
            DynamicTest.dynamicTest("딜러가 Stand고 점수가 더 낮을 경우 이긴다", () -> {
                Hand sevenPointLess = new Hand(List.of(THREE_DIAMOND, TWO_DIAMOND));
                PlayerStatus dealerStatus = Stand.of(playing, sevenPointLess);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Win.class);
            })
        );
    }
}
