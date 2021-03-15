package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class BustTest {
    @DisplayName("Bust 객체를 생성한다.")
    @Test
    public void createBust() {
        Bust bust = new Bust(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.HEART, Value.JACK)
        )));

        assertThat(bust).isInstanceOf(Bust.class);
    }

    @DisplayName("버스트 상태에서는 카드를 더 받을 수 없다.")
    @Test
    public void drawException() {
        Bust bust = new Bust(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThatIllegalStateException().isThrownBy(() -> {
            bust.draw(new Cards(Collections.singletonList(
                    new Card(Shape.CLOVER, Value.FIVE))));
        }).withMessage("종료된 상태에서는 카드를 더 받을 수 없습니다.");
    }

    @DisplayName("버스트 상태에서는 스테이 상태로 변경될 수 없다.")
    @Test
    public void stayException() {
        Bust bust = new Bust(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.CLOVER, Value.FIVE)
        )));

        assertThatIllegalStateException().isThrownBy(bust::stay)
                .withMessage("종료된 상태에서는 스테이 상태로 변경될 수 없습니다.");
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    public void isFinished() {
        Bust bust = new Bust(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.CLOVER, Value.FIVE)
        )));

        assertThat(bust.isFinished()).isTrue();
    }

    @DisplayName("플레이어가 버스트인 경우의 수익을 확인한다. - 플레이어 패")
    @Test
    public void profitPlayerLose() {
        Dealer dealer = new Dealer();
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.JACK),
                new Card(Shape.CLOVER, Value.FIVE)
        )));
        Bust player = new Bust(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.CLOVER, Value.FIVE)
        )));

        assertThat(player.profit(new Money(10000), dealer)).isEqualTo(new Money(-10000));
    }
}
