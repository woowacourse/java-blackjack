package playerState;

import static testCards.TestCards.ACE_DIAMOND;
import static testCards.TestCards.NINE_DIAMOND;
import static testCards.TestCards.QUEEN_DIAMOND;
import static testCards.TestCards.TEN_DIAMOND;
import static testCards.TestCards.TWO_DIAMOND;

import card.Hand;
import gameState.BettingAmount;
import gameState.Draw;
import gameState.Playing;
import gameState.Win;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class BlackJackTest {

    @DisplayName("BlackJack이 아닌 카드로 생성할 경우 오류를 던진다.(점수가 아님)")
    @Test
    void notBlackJackPoint() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, TWO_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Assertions.assertThatThrownBy(() -> BlackJack.of(playing, hand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("블랙잭이 아닙니다.");
    }

    @DisplayName("BlackJack이 아닌 카드로 생성할 경우 오류를 던진다.(카드가 2개가 아님)")
    @Test
    void notBlackJackCardCount() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, TWO_DIAMOND, NINE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Assertions.assertThatThrownBy(() -> BlackJack.of(playing, hand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("블랙잭이 아닙니다.");
    }

    @DisplayName("BlackJack에서 카드를 지급하면 오류를 던진다.")
    @Test
    void blackJackCanNotAddCard() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, ACE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        BlackJack blackJack = BlackJack.of(playing, hand);
        Assertions.assertThatThrownBy(() -> blackJack.addCard(TWO_DIAMOND))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("더 이상 카드를 뽑을 수 없습니다.");
    }

    @DisplayName("플레이어가 BlackJack 일 때 딜러와 비교하여 결과를 저장한다.")
    @TestFactory
    Stream<DynamicTest> playerBlackJack() {
        Hand bustHand = new Hand(List.of(TEN_DIAMOND, QUEEN_DIAMOND, TWO_DIAMOND));
        Hand standHand = new Hand(List.of(TEN_DIAMOND, QUEEN_DIAMOND));
        Hand blackJackHand = new Hand(List.of(TEN_DIAMOND, ACE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        PlayerStatus playerStatus = BlackJack.of(playing, blackJackHand);
        return Stream.of(
            DynamicTest.dynamicTest("딜러가 Bust인 경우 이긴다", () -> {
                PlayerStatus dealerStatus = Bust.of(playing, bustHand);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Win.class);
            }),
            DynamicTest.dynamicTest("딜러가 BlackJack인 경우 비긴다", () -> {
                PlayerStatus dealerStatus = BlackJack.of(playing, blackJackHand);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Draw.class);
            }),
            DynamicTest.dynamicTest("딜러가 Stand인 경우 이긴다", () -> {
                PlayerStatus dealerStatus = Stand.of(playing, standHand);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Win.class);
            })
        );
    }
}
