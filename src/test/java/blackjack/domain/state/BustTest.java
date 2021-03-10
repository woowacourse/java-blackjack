package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Card card = new Card(Suit.CLOVER, Denomination.EIGHT);

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
}
