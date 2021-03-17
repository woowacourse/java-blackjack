package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BustTest {
    @Test
    @DisplayName("bust 객체를 만든다")
    void createStay() {
        Bust bust = new Bust(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.QUEEN)));

        assertThat(bust).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("draw할 경우 예외가 발생한다.")
    void draw() {
        Bust bust = new Bust(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.QUEEN)));
        Card card = new Card(Suit.DIAMOND, Denomination.JACK);

        assertThatThrownBy(() -> bust.draw(card))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("stay할 경우 예외가 발생한다.")
    void name() {
        Bust bust = new Bust(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.QUEEN)));

        assertThatThrownBy(bust::stay)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("상태가 끝냈는지 확인한다.")
    void isFinish() {
        Bust bust = new Bust(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.QUEEN)));

        boolean isFinished = bust.isFinish();

        assertThat(isFinished).isTrue();
    }

    @Test
    @DisplayName("플레이어 버스트일 경우 수익을 확인한다.")
    void playerBust() {
        Bust bust = new Bust(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.QUEEN)));
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.THREE)
        )));
        Money money = new Money(1000);

        double result = bust.getProfit(dealer, money);

        assertThat(result).isEqualTo(-1000);
    }
}
