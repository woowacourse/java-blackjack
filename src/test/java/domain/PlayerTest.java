package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @Test
    @DisplayName("Player를 생성할 때 오류 발생 안함")
    void player_create_success() {
        Hand playerHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.J),
                new Card(CardShape.클로버, CardContents.K)
        );

        String name = "pobi";

        assertDoesNotThrow(
                () -> Player.from(name, GameState.createPlayerInitialGameState(playerHand))
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

        String testName = "gump";
        Player testPlayerWhoHoldTotal15Cards = Player.from(
                testName,
                GameState.createPlayerInitialGameState(playerHand)
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
            String testName = "gump";
            Player testPlayer = Player.from(
                    testName,
                    GameState.createPlayerInitialGameState(playerHand)
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
        String testName = "gump";
        Player testPlayer = Player.from(
                testName,
                GameState.createPlayerInitialGameState(playerHand)
        );

        //when
        testPlayer = testPlayer.stand();

        //then
        Assertions.assertThat(testPlayer.isFinished()).isTrue();
    }

    @Test
    @DisplayName("compare는 게임 결과 객체를 잘 반환한다")
    void lose_when_dealer_blackjack() {
        //given
        String testPlayerName = "rati";
        Hand blackJackHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.J),
                new Card(CardShape.클로버, CardContents.A)
        );
        Hand notBlackJackAndNotBustHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.TWO),
                new Card(CardShape.클로버, CardContents.THREE)
        );
        GameState dealerGameState = GameState.createDealerInitialGameState(blackJackHand);
        Player testPlayer = Player.from(
                testPlayerName,
                GameState.createPlayerInitialGameState(notBlackJackAndNotBustHand)
        );

        //when
        GameResult result = testPlayer.calculateGameResult(dealerGameState);

        //then
        assertEquals(GameResult.class, result.getClass());
    }

    @Test
    @DisplayName("이름이 같으면 같은 Player로 본다")
    void equal_when_name_equal() {
        String testName = "gump";
        Hand playerHand1 = Hand.of(
                new Card(CardShape.스페이드, CardContents.A),
                new Card(CardShape.클로버, CardContents.TWO)
        );
        Hand playerHand2 = Hand.of(
                new Card(CardShape.하트, CardContents.THREE),
                new Card(CardShape.클로버, CardContents.FOUR)
        );
        Player firstGump = Player.from(
                testName,
                GameState.createPlayerInitialGameState(playerHand1)
        );
        Player secondGump = Player.from(
                testName,
                GameState.createPlayerInitialGameState(playerHand2)
        );
        //when, then
        Assertions.assertThat(firstGump.equals(secondGump)).isTrue();
    }
}