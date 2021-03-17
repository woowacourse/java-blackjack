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

public class StayTest {
    @Test
    @DisplayName("stay 객체를 만든다")
    void createStay() {
        Stay stay = new Stay(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.QUEEN)));

        assertThat(stay).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("draw할 경우 예외가 발생한다.")
    void draw() {
        Stay stay = new Stay(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.QUEEN)));
        Card card = new Card(Suit.CLOVER, Denomination.EIGHT);


        assertThatThrownBy(() -> stay.draw(card))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("stay할 경우 예외가 발생한다.")
    void name() {
        Stay stay = new Stay(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.QUEEN)));

        assertThatThrownBy(stay::stay)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("상태가 끝냈는지 확인한다.")
    void isFinish() {
        Stay stay = new Stay(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.QUEEN)));

        boolean isFinished = stay.isFinish();

        assertThat(isFinished).isTrue();
    }

    @Test
    @DisplayName("(딜러 버스트 아님), 플레이어 총합이 더 작을 경우 수익 확인한다.")
    void playerStay() {
        Stay stay = new Stay(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)));
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.QUEEN)
        )));
        Money money = new Money(1000);

        double result = stay.getProfit(dealer, money);

        assertThat(result).isEqualTo(-1000);
    }

    @Test
    @DisplayName("(딜러 버스트 아님), 플레이어 총합이 더 클 경우 수익 확인한다.")
    void playerStay2() {
        Stay stay = new Stay(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)));
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.TWO),
                new Card(Suit.CLOVER, Denomination.THREE)
        )));
        Money money = new Money(1000);

        double result = stay.getProfit(dealer, money);

        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("(딜러 버스트 아님), 플레이어 총합이 같을 경우 수익 확인한다.")
    void playerStay3() {
        Stay stay = new Stay(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)));
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.TWO),
                new Card(Suit.CLOVER, Denomination.QUEEN)
        )));
        Money money = new Money(1000);

        double result = stay.getProfit(dealer, money);

        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("(딜러 버스트), 플레이어 수익 확인한다.")
    void playerStay4() {
        Stay stay = new Stay(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)));
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.QUEEN)
        )));
        dealer.hit(new Card(Suit.SPACE, Denomination.THREE));
        Money money = new Money(1000);

        double result = stay.getProfit(dealer, money);

        assertThat(result).isEqualTo(1000);
    }
}
