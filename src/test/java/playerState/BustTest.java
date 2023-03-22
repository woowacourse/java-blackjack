package playerState;

import static testCards.TestCards.ACE_DIAMOND;
import static testCards.TestCards.QUEEN_DIAMOND;
import static testCards.TestCards.SEVEN_DIAMOND;
import static testCards.TestCards.TEN_DIAMOND;
import static testCards.TestCards.THREE_DIAMOND;
import static testCards.TestCards.TWO_DIAMOND;

import card.Hand;
import gameState.BettingAmount;
import gameState.Draw;
import gameState.Lose;
import gameState.Playing;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class BustTest {

    @DisplayName("Bust가 아닌 카드로 생성할 경우 오류를 던진다.")
    @Test
    void notBust() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, SEVEN_DIAMOND, THREE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Assertions.assertThatThrownBy(() -> Bust.of(playing, hand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Bust가 아닙니다.");
    }

    @DisplayName("Bust에서 카드를 지급하면 오류를 던진다.")
    @Test
    void bustCanNotAddCard() {
        Hand hand = new Hand(List.of(TEN_DIAMOND, QUEEN_DIAMOND, THREE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Bust bust = Bust.of(playing, hand);
        Assertions.assertThatThrownBy(() -> bust.addCard(TWO_DIAMOND))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("더 이상 카드를 뽑을 수 없습니다.");
    }

    @DisplayName("플레이어가 Bust 일 때 딜려와 비교하여 결과를 저장한다.")
    @TestFactory
    Stream<DynamicTest> playerBust() {
        Hand bustHand = new Hand(List.of(TEN_DIAMOND, QUEEN_DIAMOND, TWO_DIAMOND));
        Hand standHand = new Hand(List.of(TEN_DIAMOND, QUEEN_DIAMOND));
        Hand blackJackHand = new Hand(List.of(QUEEN_DIAMOND, ACE_DIAMOND));
        Playing playing = new Playing(new BettingAmount(1000));
        Bust playerStatus = Bust.of(playing, bustHand);
        return Stream.of(
            DynamicTest.dynamicTest("딜러가 Bust인 경우 비긴다", () -> {
                PlayerStatus dealerStatus = Bust.of(playing, bustHand);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Draw.class);
            }),
            DynamicTest.dynamicTest("딜러가 BlackJack인 경우 진다", () -> {
                PlayerStatus dealerStatus = BlackJack.of(playing, blackJackHand);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Lose.class);
            }),
            DynamicTest.dynamicTest("딜러가 Stand인 경우 진다", () -> {
                PlayerStatus dealerStatus = Stand.of(playing, standHand);
                Assertions.assertThat(playerStatus.getFinalState(dealerStatus))
                    .extracting("gameStatus")
                    .isInstanceOf(Lose.class);
            })
        );
    }
}
