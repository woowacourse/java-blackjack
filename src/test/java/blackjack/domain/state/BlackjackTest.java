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

public class BlackjackTest {
    @DisplayName("Blackjack 객체를 생성한다.")
    @Test
    public void createBlackjack() {
        Blackjack blackjack = new Blackjack(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(blackjack).isInstanceOf(Blackjack.class);
    }

    @DisplayName("블랙잭 상태에서는 카드를 더 받을 수 없다.")
    @Test
    public void drawException() {
        Blackjack blackjack = new Blackjack(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThatIllegalStateException().isThrownBy(() -> {
            blackjack.draw(new Cards(Collections.singletonList(
                    new Card(Shape.HEART, Value.FIVE))));
        }).withMessage("종료된 상태에서는 카드를 더 받을 수 없습니다.");
    }

    @DisplayName("블랙잭 상태에서는 스테이 상태로 변경될 수 없다.")
    @Test
    public void stayException() {
        Blackjack blackjack = new Blackjack(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThatIllegalStateException().isThrownBy(blackjack::stay)
                .withMessage("종료된 상태에서는 스테이 상태로 변경될 수 없습니다.");
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    public void isFinished() {
        Blackjack blackjack = new Blackjack(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(blackjack.isFinished()).isTrue();
    }

    @DisplayName("딜러와 플레이어가 모두 블랙잭인 경우의 수익을 확인한다. - 무승부")
    @Test
    public void profitStandoff() {
        Dealer dealer = new Dealer();
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Blackjack player = new Blackjack(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(player.profit(new Money(10000), dealer)).isEqualTo(new Money(0));
    }

    @DisplayName("플레이어가 블랙잭인 경우의 수익을 확인한다. - 플레이어 승")
    @Test
    public void profitPlayerWin() {
        Dealer dealer = new Dealer();
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.TWO),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Blackjack player = new Blackjack(new Cards(Arrays.asList(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(player.profit(new Money(10000), dealer)).isEqualTo(new Money(15000));
    }
}
