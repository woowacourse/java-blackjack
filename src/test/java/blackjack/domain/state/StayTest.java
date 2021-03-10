package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Card card = new Card(Suit.CLOVER, Denomination.EIGHT);

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
}
