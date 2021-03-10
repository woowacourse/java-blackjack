package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {
    @Test
    @DisplayName("hit 객체를 만든다.")
    void createHit() {
        Hit hit = new Hit(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.HEART, Denomination.ACE)));

        assertThat(hit).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("상태를 확인하고 draw 한다.")
    void hitDraw1() {
        Hit hit = new Hit(new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.JACK)));

        State state = hit.draw(new Card(Suit.CLOVER, Denomination.EIGHT));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("상태를 확인하고 draw 한다.")
    void hitDraw2() {
        Hit hit = new Hit(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.HEART, Denomination.ACE)));

        State state = hit.draw(new Card(Suit.CLOVER, Denomination.EIGHT));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드 받기 멈춤 상태를 확인한다.")
    void stay() {
        Hit hit = new Hit(new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.HEART, Denomination.ACE)));

        State state = hit.stay();

        assertThat(state).isInstanceOf(Stay.class);
    }
}