package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.GamePoint;
import blackjack.domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GamePointTest {

    @Test
    @DisplayName("카드 게임 포인트는 카드들을 기반으로 값을 계산한다.")
    void gamePointCalculateTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.TWO),
                new Card(Shape.DIAMOND, CardNumber.THREE),
                new Card(Shape.HEART, CardNumber.FOUR)
        );

        //when
        final GamePoint gamePoint = new GamePoint(data);
        final int value = gamePoint.getValue();

        //then
        assertThat(value).isEqualTo(9);
    }

    @Test
    @DisplayName("A는 1혹은 11로 계산할 수 있으면 해당 카드들의 최선 값을 반환한다.")
    void optimizeCalculateTest1() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.DIAMOND, CardNumber.TWO),
                new Card(Shape.HEART, CardNumber.THREE)
        );

        //when
        final GamePoint gamePoint = new GamePoint(data);
        final int value = gamePoint.getValue();

        //then
        assertThat(value).isEqualTo(16);
    }

    @Test
    @DisplayName("카드 계산 값이 21이 넘었을 경우 burst처리 한다.")
    void optimizeCalculateTest2() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.JACK),
                new Card(Shape.DIAMOND, CardNumber.KING),
                new Card(Shape.HEART, CardNumber.TWO)
        );

        //when
        final GamePoint gamePoint = new GamePoint(data);
        final int value = gamePoint.getValue();

        //then
        assertThat(value).isEqualTo(0);
    }

    @Test
    @DisplayName("카드 계산 값이 21 초과이고, ACE값이 있다면 ACE 값은 1로 계산한다.")
    void optimizeCalculateTest3() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.JACK),
                new Card(Shape.DIAMOND, CardNumber.KING),
                new Card(Shape.HEART, CardNumber.ACE)
        );

        // when
        final GamePoint gamePoint = new GamePoint(data);
        final int value = gamePoint.getValue();

        // then
        assertThat(value).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 계산 값이 21 초과이고, ACE값이 있다면 ACE 값은 1로 계산한다.")
    void optimizeCalculateTest4() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.DIAMOND, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE)
        );

        //when
        final GamePoint gamePoint = new GamePoint(data);
        final int value = gamePoint.getValue();

        //then
        assertThat(value).isEqualTo(16);
    }

    @Test
    @DisplayName("BlackJack 카드 두 장으로 21점을 만들면 된다.")
    void BlackJackTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.DIAMOND, CardNumber.TEN)
        );

        //when
        final GamePoint gamePoint = new GamePoint(data);
        final boolean isBlackJack = gamePoint.isBlackJack();

        //then
        assertTrue(isBlackJack);
    }

    @Test
    @DisplayName("BlackJack 카드 세 장으로 21점을 만든 것은 블랙잭이 아니다.")
    void notBlackJackTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.DIAMOND, CardNumber.TEN),
                new Card(Shape.DIAMOND, CardNumber.TEN)
        );

        //when
        final GamePoint gamePoint = new GamePoint(data);
        final boolean isBlackJack = gamePoint.isBlackJack();

        //then
        assertFalse(isBlackJack);
    }
}
