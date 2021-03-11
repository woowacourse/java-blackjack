package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BlackjackTest {
    @Test
    @DisplayName("blackjack 객체를 만든다")
    void createStay() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.ACE)));

        assertThat(blackjack).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("draw할 경우 예외가 발생한다.")
    void draw() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.ACE)));
        Card card = new Card(Suit.DIAMOND, Denomination.JACK);


        assertThatThrownBy(() -> blackjack.draw(card))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("stay할 경우 예외가 발생한다.")
    void name() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.ACE)));
        Card card = new Card(Suit.CLOVER, Denomination.EIGHT);

        assertThatThrownBy(blackjack::stay)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("상태가 끝냈는지 확인한다.")
    void isFinish() {
        Blackjack blackjack = new Blackjack(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.ACE)));

        boolean isFinished = blackjack.isFinish();

        assertThat(isFinished).isTrue();
    }
}
