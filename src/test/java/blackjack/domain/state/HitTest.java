package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class HitTest {
    private Hit hit;

    @BeforeEach
    public void setUp() {
        hit = new Hit(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.EIGHT),
                new Card(Shape.CLOVER, Value.FIVE)
        )));
    }

    @DisplayName("Hit 객체를 생성한다.")
    @Test
    public void createHit() {
        assertThat(hit).isInstanceOf(Hit.class);
    }

    @DisplayName("힛 상태에서는 카드를 더 받을 수 있다. - 버스트가 되는 경우")
    @Test
    public void drawToBust() {
        State state = hit.draw(new Cards(Collections.singletonList(
                new Card(Shape.HEART, Value.JACK))));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @DisplayName("힛 상태에서는 카드를 더 받을 수 있다. - 힛이 되는 경우")
    @Test
    public void drawToHit() {
        State state = hit.draw(new Cards(Collections.singletonList(
                new Card(Shape.HEART, Value.THREE))));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("힛 상태에서는 스테이 상태로 변경될 수 있다.")
    @Test
    public void stay() {
        State state = hit.stay();

        assertThat(state).isInstanceOf(Stay.class);
    }

    @DisplayName("힛 상태에서는 최종 수익을 구할 수 없다.")
    @Test
    public void profitException() {
        assertThatIllegalStateException().isThrownBy(() -> {
            hit.profit(new Money(10000), new Dealer());
        }).withMessage("실행중인 상태에서는 최종 수익을 구할 수 없습니다.");
    }

    @DisplayName("종료된 상태가 아닌지 확인한다.")
    @Test
    public void isNotFinished() {
        assertThat(hit.isFinished()).isFalse();
    }

    @DisplayName("힛 상태에서는 수익률을 구할 수 없다.")
    @Test
    public void profitRateException() {
        assertThatIllegalStateException().isThrownBy(() -> {
            hit.profitRate(new Dealer());
        }).withMessage("실행중인 상태에서는 수익률을 구할 수 없습니다.");
    }
}
