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

public class StayTest {
    @DisplayName("Stay 객체를 생성한다.")
    @Test
    public void createStay() {
        Stay stay = new Stay(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(stay).isInstanceOf(Stay.class);
    }

    @DisplayName("스테이 상태에서는 카드를 더 받을 수 없다.")
    @Test
    public void drawException() {
        Stay stay = new Stay(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThatIllegalStateException().isThrownBy(() -> {
            stay.draw(new Cards(Collections.singletonList(
                    new Card(Shape.CLOVER, Value.FIVE))));
        }).withMessage("종료된 상태에서는 카드를 더 받을 수 없습니다.");
    }

    @DisplayName("스테이 상태에서는 스테이 상태로 변경될 수 없다.")
    @Test
    public void stayException() {
        Stay stay = new Stay(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThatIllegalStateException().isThrownBy(stay::stay)
                .withMessage("종료된 상태에서는 스테이 상태로 변경될 수 없습니다.");
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    public void isFinished() {
        Stay stay = new Stay(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(stay.isFinished()).isTrue();
    }

    @DisplayName("딜러가 버스트인 경우의 수익을 확인한다. - 플레이어 승")
    @Test
    public void profitPlayerWinDealerBust() {
        Stay player = new Stay(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Dealer dealer = new Dealer();
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.DIAMOND, Value.JACK)
        )));
        dealer.hit(new Cards(Collections.singletonList(
                new Card(Shape.CLOVER, Value.SEVEN))));

        assertThat(player.profit(new Money(10000), dealer)).isEqualTo(new Money(10000));
    }

    @DisplayName("딜러가 버스트가 아니고 딜러보다 플레이어의 카드 총합이 더 큰 경우의 수익을 확인한다. - 플레이어 승")
    @Test
    public void profitPlayerWinDealerNotBust() {
        Stay player = new Stay(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Dealer dealer = new Dealer();
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.DIAMOND, Value.SIX)
        )));

        assertThat(player.profit(new Money(10000), dealer)).isEqualTo(new Money(10000));
    }

    @DisplayName("딜러가 버스트가 아니고 딜러와 플레이어의 카드 총합이 같은 경우의 수익을 확인한다. - 무승부")
    @Test
    public void profitStandoffDealerNotBust() {
        Stay player = new Stay(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Dealer dealer = new Dealer();
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.DIAMOND, Value.JACK)
        )));

        assertThat(player.profit(new Money(10000), dealer)).isEqualTo(new Money(0));
    }

    @DisplayName("딜러가 버스트가 아니고 딜러보다 플레이어의 카드 총합이 더 작은 경우의 수익을 확인한다. - 플레이어 패")
    @Test
    public void profitPlayerLostDealerNotBust() {
        Stay player = new Stay(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Dealer dealer = new Dealer();
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.DIAMOND, Value.JACK)
        )));

        assertThat(player.profit(new Money(10000), dealer)).isEqualTo(new Money(-10000));
    }
}
