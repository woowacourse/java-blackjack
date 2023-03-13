package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.game.GamePoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GamePointTest {

    @Test
    @DisplayName("카드 게임 포인트는 카드들을 기반으로 값을 계산한다.")
    void gamePointCalculateTest() {
        final List<Card> data = List.of(
                Card.of(CardShape.CLOVER, CardNumber.of(2)),
                Card.of(CardShape.DIAMOND, CardNumber.of(3)),
                Card.of(CardShape.HEART, CardNumber.of(4))
        );

        final GamePoint gamePoint = GamePoint.create(data);
        assertThat(gamePoint.getPoint()).isSameAs(9);
    }

    @Test
    @DisplayName("A는 1혹은 11로 계산할 수 있으면 해당 카드들의 최적값을 반환한다.")
    void optimizeCalculateTest1() {
        final List<Card> data = List.of(
                Card.of(CardShape.CLOVER, CardNumber.of(1)),
                Card.of(CardShape.DIAMOND, CardNumber.of(2)),
                Card.of(CardShape.HEART, CardNumber.of(3))
        );

        final GamePoint gamePoint = GamePoint.create(data);
        assertThat(gamePoint.getPoint()).isSameAs(16);
    }

    @Test
    @DisplayName("카드 계산 값이 21이 넘었을 경우 bust(0점)처리 한다.")
    void optimizeCalculateTest2() {
        final List<Card> data = List.of(
                Card.of(CardShape.CLOVER, CardNumber.of(12)),
                Card.of(CardShape.DIAMOND, CardNumber.of(13)),
                Card.of(CardShape.HEART, CardNumber.of(2))
        );
        final GamePoint gamePoint = GamePoint.create(data);
        assertThat(gamePoint.getPoint()).isSameAs(0);
    }

    @Test
    @DisplayName("카드 계산 값이 21 초과이고, ACE값이 있다면 ACE 값은 1로 계산한다.")
    void optimizeCalculateTest3() {
        final List<Card> data = List.of(
                Card.of(CardShape.CLOVER, CardNumber.of(12)),
                Card.of(CardShape.DIAMOND, CardNumber.of(13)),
                Card.of(CardShape.HEART, CardNumber.of(1))
        );
        final GamePoint gamePoint = GamePoint.create(data);
        assertThat(gamePoint.getPoint()).isSameAs(21);
    }

    @Test
    @DisplayName("카드 계산 값이 21 초과이고, ACE값이 있다면 ACE 값은 1로 계산한다.")
    void optimizeCalculateTest4() {
        final List<Card> data = List.of(
                Card.of(CardShape.CLOVER, CardNumber.of(1)),
                Card.of(CardShape.DIAMOND, CardNumber.of(1)),
                Card.of(CardShape.HEART, CardNumber.of(1)),
                Card.of(CardShape.HEART, CardNumber.of(1)),
                Card.of(CardShape.HEART, CardNumber.of(1)),
                Card.of(CardShape.HEART, CardNumber.of(1))
        );
        final GamePoint gamePoint = GamePoint.create(data);
        assertThat(gamePoint.getPoint()).isSameAs(16);
    }
}
