package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.GamePoint;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GamePointTest {

    @Test
    @DisplayName("카드 게임 포인트는 카드들을 기반으로 값을 계산한다.")
    void gamePointCalculateTest() {
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.TWO),
                new Card(Shape.DIAMOND, CardNumber.THREE),
                new Card(Shape.HEART, CardNumber.FOUR)
        );

        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getValue()).isEqualTo(9);
    }

    @Test
    @DisplayName("A는 1혹은 11로 계산할 수 있으면 해당 카드들의 최선 값을 반환한다.")
    void optimizeCalculateTest1() {
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.DIAMOND, CardNumber.TWO),
                new Card(Shape.HEART, CardNumber.THREE)
        );

        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getValue()).isEqualTo(16);
    }

    @Test
    @DisplayName("카드 계산 값이 21이 넘었을 경우 burst처리 한다.")
    void optimizeCalculateTest2() {
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.JACK),
                new Card(Shape.DIAMOND, CardNumber.KING),
                new Card(Shape.HEART, CardNumber.TWO)
        );
        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("카드 계산 값이 21 초과이고, ACE값이 있다면 ACE 값은 1로 계산한다.")
    void optimizeCalculateTest3() {
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.JACK),
                new Card(Shape.DIAMOND, CardNumber.KING),
                new Card(Shape.HEART, CardNumber.ACE)
        );
        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getValue()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 계산 값이 21 초과이고, ACE값이 있다면 ACE 값은 1로 계산한다.")
    void optimizeCalculateTest4() {
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.DIAMOND, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE)
        );
        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getValue()).isEqualTo(16);
    }
}
