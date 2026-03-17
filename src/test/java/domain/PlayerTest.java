package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import common.ErrorMessage;
import domain.state.GameState;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private static final String GUMP = "gump";

    @Test
    @DisplayName("Player를 생성할 때 오류 발생 안함")
    void player_create_success() {
        Hand playerHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.J),
                new Card(CardShape.클로버, CardContents.K)
        );

        assertDoesNotThrow(
                () -> Player.from(GUMP, GameState.createInitialGameState(playerHand))
        );
    }

    @Nested
    class hitTest {
        //given
        Hand playerHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.J),
                new Card(CardShape.클로버, CardContents.FIVE)
        );

        Queue<Card> onlyTwoTenCards = new LinkedList<>(List.of(
                new Card(CardShape.하트, CardContents.TEN),
                new Card(CardShape.하트, CardContents.J)
        ));
        Supplier<Card> onlyTwoTenCardSupplier = onlyTwoTenCards::poll;

        Player testPlayerWhoHoldTotal15Cards = Player.from(
                GUMP,
                GameState.createInitialGameState(playerHand)
        );

        @Test
        @DisplayName("hit 할 수 있는 상태이면 hit를 진행한다")
        void hit_do() {
            //given
            Queue<Card> testDeck = new LinkedList<>(List.of(
                    new Card(CardShape.클로버, CardContents.SEVEN)
            ));
            Supplier<Card> testCardSupplier = () -> testDeck.poll();

            //when
            testPlayerWhoHoldTotal15Cards = testPlayerWhoHoldTotal15Cards.hit(testCardSupplier);

            //then
            Assertions.assertThat(testCardSupplier.get()).isNull();
        }

        @Test
        @DisplayName("hit 할 수 없는 상태이면 카드 뽑기를 내부적으로 카드를 뽑지 못하도록 오류를 던진다")
        void hit_do_not() {
            //given
            testPlayerWhoHoldTotal15Cards = testPlayerWhoHoldTotal15Cards.hit(onlyTwoTenCardSupplier);

            //when
            assertThatThrownBy(
                    () -> testPlayerWhoHoldTotal15Cards.hit(onlyTwoTenCardSupplier)
            ).isInstanceOf(IllegalStateException.class)
                    .hasMessage(ErrorMessage.NOT_ALLOW_METHOD_CALL.getMessage());
        }

        @Test
        @DisplayName("hit를 요구했지만 isStay가 false이지만 bust 상태여도 변화가 없다")
        void hit_butBust_and_finish() {
            //given
            Hand playerHand = Hand.of(
                    new Card(CardShape.스페이드, CardContents.TEN),
                    new Card(CardShape.클로버, CardContents.TEN)
            );
            Player testPlayer = Player.from(
                    GUMP,
                    GameState.createInitialGameState(playerHand)
            );
            testPlayer.hit(onlyTwoTenCardSupplier);

            //when
            Player hitResultPlayer = testPlayer.hit(onlyTwoTenCards::poll);

            //then
            assertEquals(testPlayer.hashCode(), hitResultPlayer.hashCode());
        }
    }

    @Test
    @DisplayName("stay를 호출하면 새로운 사용자를 반환하고 그 사용자는 게임을 종료한 상태가 된다")
    void stand_and_finish() {
        //given
        Hand playerHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.J),
                new Card(CardShape.클로버, CardContents.FIVE)
        );
        Player testPlayer = Player.from(
                GUMP,
                GameState.createInitialGameState(playerHand)
        );

        //when
        testPlayer = testPlayer.stand();

        //then
        Assertions.assertThat(testPlayer.isFinished()).isTrue();
    }

    @Test
    @DisplayName("이름이 같으면 같은 Player로 본다")
    void equal_when_name_equal() {
        Hand playerHand1 = Hand.of(
                new Card(CardShape.스페이드, CardContents.A),
                new Card(CardShape.클로버, CardContents.TWO)
        );
        Hand playerHand2 = Hand.of(
                new Card(CardShape.하트, CardContents.THREE),
                new Card(CardShape.클로버, CardContents.FOUR)
        );
        Player firstGump = Player.from(
                GUMP,
                GameState.createInitialGameState(playerHand1)
        );
        Player secondGump = Player.from(
                GUMP,
                GameState.createInitialGameState(playerHand2)
        );
        //when, then
        Assertions.assertThat(firstGump.equals(secondGump)).isTrue();
    }

    @Test
    @DisplayName("베팅을 하면 베팅 한 것으로 인정된다")
    void bet_after_isBet_True() {
        //given
        Hand playerHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.A),
                new Card(CardShape.클로버, CardContents.TWO)
        );
        Player gump = Player.from(
                GUMP,
                GameState.createInitialGameState(playerHand)
        );

        String gumpBetAmountValue = "1000";

        //when
        Player bettedGump = gump.bet(gumpBetAmountValue);

        //then
        assertTrue(bettedGump.isBet());
    }

    @Nested
    class BetResultTest {
        String betAmountValue = "10000";

        Hand blackJackHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.A),
                new Card(CardShape.클로버, CardContents.TEN)
        );

        Hand normalHandFive = Hand.of(
                new Card(CardShape.스페이드, CardContents.TWO),
                new Card(CardShape.클로버, CardContents.THREE)
        );

        Hand normalHandTwenty = Hand.of(
                new Card(CardShape.스페이드, CardContents.J),
                new Card(CardShape.클로버, CardContents.K)
        );

        Hand normalHandAnotherTwenty = Hand.of(
                new Card(CardShape.하트, CardContents.J),
                new Card(CardShape.다이아몬드, CardContents.K)
        );

        @Test
        @DisplayName("본인의 베팅에 따른 수익을 잘 구한다 - 블랙잭 승리")
        void player_win_blackjack() {
            String betAmountValue = "10000";
            double blackJackAllocation = 1.5;
            Player gump = Player.from(
                    GUMP,
                    GameState.createInitialGameState(blackJackHand)
            );
            gump = gump.bet(betAmountValue);
            Dealer dealer = Dealer.from(
                    GameState.createInitialGameState(normalHandTwenty)
            );

            double expect = Integer.parseInt(betAmountValue) * blackJackAllocation;

            double result = gump.calculateEarnMoney(dealer);

            assertEquals(expect, result);
        }

        @Test
        @DisplayName("본인의 베팅에 따른 수익을 잘 구한다 - 일반 승리")
        void player_win_normal() {
            double normalAllocation = 1.0;
            Player gump = Player.from(
                    GUMP,
                    GameState.createInitialGameState(normalHandTwenty)
            );
            gump = gump.bet(betAmountValue);
            Dealer dealer = Dealer.from(
                    GameState.createInitialGameState(normalHandFive)
            );

            double expect = Integer.parseInt(betAmountValue) * normalAllocation;

            double result = gump.calculateEarnMoney(dealer);

            assertEquals(expect, result);
        }

        @Test
        @DisplayName("본인의 베팅에 따른 수익을 잘 구한다 - 무승부")
        void player_draw() {
            String betAmountValue = "10000";
            Player gump = Player.from(
                    GUMP,
                    GameState.createInitialGameState(normalHandTwenty)
            );
            gump = gump.bet(betAmountValue);
            Dealer dealer = Dealer.from(
                    GameState.createInitialGameState(normalHandAnotherTwenty)
            );

            double expect = 0;

            double result = gump.calculateEarnMoney(dealer);

            assertEquals(expect, result);
        }
    }
}