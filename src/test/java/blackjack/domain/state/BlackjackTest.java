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

public class BlackjackTest {
    @Test
    @DisplayName("blackjack 객체를 만든다")
    void createStay() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.JACK)));

        assertThat(blackjack).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("draw할 경우 예외가 발생한다.")
    void draw() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.JACK)));
        Card card = new Card(Suit.DIAMOND, Denomination.JACK);


        assertThatThrownBy(() -> blackjack.draw(card))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("stay할 경우 예외가 발생한다.")
    void name() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.JACK)));

        assertThatThrownBy(blackjack::stay)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("상태가 끝냈는지 확인한다.")
    void isFinish() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.JACK)));

        boolean isFinished = blackjack.isFinish();

        assertThat(isFinished).isTrue();
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭인 경우 수익을 확인한다.")
    void dealerAndPlayerBlackjack() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.JACK)));
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.ACE)
        )));
        Money money = new Money(1000);

        double result = blackjack.getProfit(dealer, money);

        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어만 블랙잭인 경우 수익을 확인한다.")
    void dealerStay() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.JACK)));
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.SIX),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));
        Money money = new Money(1000);

        double result = blackjack.getProfit(dealer, money);

        assertThat(result).isEqualTo(1500);
    }
}
